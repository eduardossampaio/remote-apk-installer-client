package remoteapkinstallerclient.com.br.remoteinstaller.view.adapter

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import java.io.UnsupportedEncodingException
import java.util.Date

import remoteapkinstallerclient.com.br.remoteinstaller.R
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App
import remoteapkinstallerclient.com.br.remoteinstaller.service.download.DownloadService
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils

/**
 * Created by joao.roriz on 04/12/17.
 */

class AppsAdapter(private val apps: List<App>?, private val context: Context) : RecyclerView.Adapter<AppsAdapter.AppsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppsViewHolder, position: Int) {
        val app = apps!![position]

        holder.tvName.text = app.appName
        holder.tvHour.text = DateUtils.toString("dd/MM/yyyy", Date(app.addedDate!!)) + " - " + DateUtils.toString("HH:mm", Date(app.addedDate!!))
        holder.tvPackage.text = app.packageName

        holder.btnInstall.setOnClickListener {
            val mProgressDialog: ProgressDialog
            try {
                DownloadService().download(context, app)
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
            }
        }

        holder.btnChangelogs.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle(app.appName)

            var message = ""
            if (app.changelog != null && app.changelog!!.isEmpty()) {
                message = "Nenhum changlog registrado"
            } else {
                for (log in app.changelog!!) {
                    message = message + log + "\n"
                }
            }
            alertDialog.setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, id -> dialog.cancel() }

            alertDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return apps?.size ?: 0
    }

    class AppsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView
        var tvPackage: TextView
        var tvHour: TextView
        var btnChangelogs: Button
        var btnInstall: Button

        init {

            tvName = itemView.findViewById(R.id.tv_nome)
            tvHour = itemView.findViewById(R.id.tv_hour)
            tvPackage = itemView.findViewById(R.id.tv_app_package)
            btnChangelogs = itemView.findViewById(R.id.btn_changelos)
            btnInstall = itemView.findViewById(R.id.btn_install)

        }
    }
}
