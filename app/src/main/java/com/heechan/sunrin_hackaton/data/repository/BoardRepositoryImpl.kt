package com.heechan.sunrin_hackaton.data.repository

import android.net.Uri
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.heechan.sunrin_hackaton.data.model.Accident
import com.heechan.sunrin_hackaton.uitl.Result
import kotlinx.coroutines.tasks.await
import java.text.SimpleDateFormat
import java.util.*

class BoardRepositoryImpl : BoardRepository{
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
    val storage : FirebaseStorage = FirebaseStorage.getInstance()
    val storageRef = storage.reference

    override suspend fun saveBoard(boardData: Accident, img: Uri): Int {
        var result : Int = Result.faild

        val saveImgResult = saveBoardImage(img, boardData.writer) ?: return result

        boardData.imgUrl = saveImgResult

        db.collection("board").add(boardData)
            .addOnSuccessListener {
                result = Result.ok
            }
            .await()

        return result
    }

    override suspend fun saveBoardImage(img: Uri, writerId: String): String? {
        var downloadUri : String? = null

        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageName = "${timeStamp}_${writerId}.png"

        val imgRef = storageRef.child("board").child(imageName)

        imgRef.putFile(img)
            .continueWithTask{
                return@continueWithTask imgRef.downloadUrl
            }
            .addOnSuccessListener {
                downloadUri = it.toString()
            }
            .await()

        return downloadUri
    }

    override suspend fun getAllBoard(): Map<String, Accident> {
        val boardList = mutableMapOf<String, Accident>()
        db.collection("board").get()
            .addOnSuccessListener {
                for (document in it){
                    boardList[document.id] = document.toObject(Accident::class.java)
                }
            }.await()

        return boardList
    }

}