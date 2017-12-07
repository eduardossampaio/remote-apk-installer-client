package remoteapkinstallerclient.com.br.remoteinstaller.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import remoteapkinstallerclient.com.br.remoteinstaller.R;
import remoteapkinstallerclient.com.br.remoteinstaller.objects.App;
import remoteapkinstallerclient.com.br.remoteinstaller.utils.DateUtils;

/**
 * Created by eduardo.sampaio on 07/12/17.
 */

public class ListApksAdapter extends  RecyclerView.Adapter<ListApksAdapter.AppsViewHolder>{

    private List<App> apps;
    private Context context;

    public ListApksAdapter(List<App> apps, Context context) {
        this.apps = apps;
        this.context = context;
    }

    @Override
    public ListApksAdapter.AppsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.apk_list_item, parent, false);
        return  new ListApksAdapter.AppsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListApksAdapter.AppsViewHolder holder, int position) {
        final App app = apps.get(position);

        holder.tvName.setText(app.getName());
        holder.tvHour.setText(DateUtils.toString("dd/MM/yyyy",new Date(app.getAddedDate())) +" - "+ DateUtils.toString("HH:mm", new Date(app.getAddedDate()) ));
        holder.tvAppVersion.setText("Versão: "+app.getVersionName());

    }

    @Override
    public int getItemCount() {
        return apps==null ? 0 : apps.size();
    }

    class AppsViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        TextView tvAppVersion;
        TextView tvHour;
        ImageView icone;

        public AppsViewHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_nome);
            tvAppVersion = itemView.findViewById(R.id.tv_app_version);
            tvHour = itemView.findViewById(R.id.tv_hour);
            icone = itemView.findViewById(R.id.app_icon);

        }
    }
}
