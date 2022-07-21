package com.heechan.sunrin_hackaton.data.repository

import android.net.Uri
import com.heechan.sunrin_hackaton.data.model.Accident

interface BoardRepository {
    suspend fun saveBoard(boardData : Accident, img : Uri) : Int
    suspend fun saveBoardImage(img : Uri, writerId : String) : String?

    suspend fun getAllBoard() : Map<String, Accident>

//    suspend fun readersLikeListUpdate(likeBoardList : ArrayList<String>, readersUserID : String) : BoardResult
//    suspend fun writerLikeCountUpdate(totalLikeCount : Int, writerId: String) : BoardResult
//    suspend fun boardLikeListUpdate(likeUserList : ArrayList<String>, boardID : String) : BoardResult
//    suspend fun getLikeBoards(likeBoardsList : ArrayList<String>) : Map<String, Board>
}