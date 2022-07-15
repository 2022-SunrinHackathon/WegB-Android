package com.heechan.sunrin_hackaton.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.heechan.sunrin_hackaton.data.model.User
import com.heechan.sunrin_hackaton.uitl.Result
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl() : AuthRepository {
    val auth : FirebaseAuth = FirebaseAuth.getInstance()
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()


    override suspend fun signUp(user: User, password: String): Int {
        //회원가입 전체를 진행하는 함수, 회원가입 결과는 AuthResult에 정의된데로 반환
        val saveUserDataResult = saveUserData(user)
        if(saveUserDataResult == Result.faild){
            //유저 정보 저장이 실패한 경우
            return Result.faild
        }

        val createAccountResult = createAccount(user, password)
        if(createAccountResult == Result.faild){
            //계정 생성이 실패한 경우 이저에 저장한 유저 정보를 삭제한다.
            db.collection("user").document(user.email).delete()
            return Result.faild
        }

        return Result.ok
    }

    override suspend fun createAccount(user: User, password: String): Int {
        //회원가입 하려는 userData와 비밀번호를 받아서 계정 생성을 진행한다. 회원가입 결과는 AuthResult에 정의된데로 반환

        var result = Result.ok
        auth.createUserWithEmailAndPassword(user.email, password)
            .addOnFailureListener {
                result = Result.faild
            }
            .await()

        return result
    }

    override suspend fun saveUserData(user: User): Int {
        //userData를 받고 데이터를 저장한다.
        var result = Result.faild

        db.collection("user").document(user.email).set(user)
            .addOnSuccessListener {
                result = Result.ok
            }
            .await()

        return result
    }


    override suspend fun getUserDataByEmail(email: String): User? {
        var resultUserData : User? = null

        db.collection("user").whereEqualTo("email", email).get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    resultUserData = document.toObject(User::class.java)
                }
            }.await()

        return resultUserData
    }

    override suspend fun login(email: String, password: String): User? {
        //로그인을 전체적으로 진행하는 함수, id와 비밀번호를 입력받아서 로그인을 진행하고
        // 로그인에 성공했다면 id에 해당하는 유저의 데이터를, 실패했다면 null을 반환한다.

        var user : User? = getUserDataByEmail(email)

        if(user == null){
            return null
        }

        try{
            auth.signInWithEmailAndPassword(user.email, password).await()
        }   catch (e : com.google.firebase.auth.FirebaseAuthInvalidCredentialsException){
            user = null
        }

        return user
    }
}