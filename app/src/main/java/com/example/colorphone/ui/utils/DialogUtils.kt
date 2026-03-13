package com.example.colorphone.ui.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtils {
    fun showConfirmDeleteDialog(
        context: Context,
        roomName: String,
        onConfirm: () -> Unit
    ) {
        AlertDialog.Builder(context)
            .setTitle("Xóa phòng")
            .setMessage("Bạn có chắc chắn muốn xóa phòng $roomName không?")
            .setPositiveButton("Xóa") { dialog, _ ->
                onConfirm()
                dialog.dismiss()
            }
            .setNegativeButton("Hủy") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
