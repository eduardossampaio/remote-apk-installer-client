package remoteapkinstallerclient.com.br.remoteinstaller.view.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import java.util.Date

import remoteapkinstallerclient.com.br.remoteinstaller.R
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils
import remoteapkinstallerclient.com.br.remoteinstaller.view.Activities.AppDetailsActivity
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.util.Base64


/**
 * Created by eduardo.sampaio on 07/12/17.
 */

class ListApksAdapter(private val apps: List<App>?, private val context: Context) : RecyclerView.Adapter<ListApksAdapter.AppsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListApksAdapter.AppsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.apk_list_item, parent, false)
        return ListApksAdapter.AppsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListApksAdapter.AppsViewHolder, position: Int) {
        val app = apps!![position]

        holder.tvName.text = app.appName
        holder.tvHour.text = DateUtils.toString("dd/MM/yyyy", Date(app.addedDate!!)) + " - " + DateUtils.toString("HH:mm", Date(app.addedDate!!))
        holder.tvAppVersion.text = "Versão: " + app.versionName!!
        holder.itemView.setOnClickListener {
            val intent = Intent(context, AppDetailsActivity::class.java)
            intent.putExtra(AppDetailsActivity.EXTRA_APP, app)
            context.startActivity(intent)
        }


        holder.icone.setImageBitmap(app.getBitmapIcon());

    }

    override fun getItemCount(): Int {
        return apps?.size ?: 0
    }

    class AppsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvAppVersion: TextView
        var tvHour: TextView
        var icone: ImageView

        init {

            tvName = itemView.findViewById(R.id.tv_nome)
            tvAppVersion = itemView.findViewById(R.id.tv_app_version)
            tvHour = itemView.findViewById(R.id.tv_hour)
            icone = itemView.findViewById(R.id.app_icon)

        }
    }
}
