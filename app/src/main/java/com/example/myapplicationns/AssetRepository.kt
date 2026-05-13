package com.example.myapplicationns

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

object AssetRepository {
    private val db = FirebaseFirestore.getInstance()
    private val assetsCollection = db.collection("assets")

    suspend fun getAllAssets(): Result<List<Asset>> = try {
        val snapshot = assetsCollection.get().await()
        val assets = snapshot.documents.mapNotNull { doc ->
            doc.toObject(Asset::class.java)?.copy(id = doc.id)
        }
        Result.success(assets)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun addAsset(asset: Asset): Result<Unit> = try {
        assetsCollection.add(asset).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun updateAssetCondition(assetId: String, newCondition: String): Result<Unit> = try {
        assetsCollection.document(assetId).update("condition", newCondition).await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun deleteAsset(assetId: String): Result<Unit> = try {
        assetsCollection.document(assetId).delete().await()
        Result.success(Unit)
    } catch (e: Exception) {
        Result.failure(e)
    }
}
