package remoteapkinstallerclient.com.br.remoteinstaller.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.R;
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils;

/**
 * Created by joao.roriz on 04/12/17.
 */

public class AppsAdapter extends  RecyclerView.Adapter<AppsAdapter.AppsViewHolder>{
    private List<App> apps;
    private Context context;

    public AppsAdapter(List<App> apps, Context context) {
        this.apps = apps;
        this.context = context;
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_app, null, false);
        return  new AppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppsViewHolder holder, int position) {
        final App app = apps.get(position);

        holder.tvName.setText(app.getName());
        holder.tvHour.setText(DateUtils.toString("dd/MM/yyyy",new Date(app.getAddedDate())) +" - "+ DateUtils.toString("HH:mm", new Date(app.getAddedDate()) ));
        holder.tvPackage.setText(app.getPackageName());

        holder.btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Aqui vai instalar", Toast.LENGTH_SHORT).show();
            }
        });

        holder.btnChangelogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("CHANGUELOG");
                alertDialog.setMessage(app.getChangelogs())
                        .setCancelable(false)
                        .setPositiveButton("Ok",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });

                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    class AppsViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvPackage;
        TextView tvHour;
        Button btnChangelogs;
        Button btnInstall;

        public AppsViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_nome);
            tvHour = itemView.findViewById(R.id.tv_hour);
            tvPackage = itemView.findViewById(R.id.tv_app_package);
            btnChangelogs = itemView.findViewById(R.id.btn_changelos);
            btnInstall = itemView.findViewById(R.id.btn_install);

        }
    }
}
