package com.medical.citylap.Adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.medical.citylap.R;
import com.medical.citylap.modles.Datum;
import com.medical.citylap.modles.Result;
import com.medical.citylap.modles.ResultApi;
import com.medical.citylap.modles.Resultcopy;
import com.medical.citylap.modles.Resultss;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class AdapterResult extends RecyclerView.Adapter<AdapterResult.ViewHolder2> {
    ResultApi resultApi;
    ArrayList<Result> listresult=new ArrayList<>();
    ArrayList<Resultss> listresultapi=new ArrayList<>();
    private Context mContext;
    Uri uri;
    ArrayList< Resultcopy>re=new ArrayList<>();
    public void setlist2(ResultApi resultApi,ArrayList< Resultcopy> re){
        this.resultApi=resultApi;
        this.re=re;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public AdapterResult.ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_result_befor,parent,false);
        return new AdapterResult.ViewHolder2(view);
    }
    public AdapterResult( Context mContext) {
        this.mContext = mContext;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterResult.ViewHolder2 holder, int position) {

         boolean isExpand=re.get(position).isExpand();
        holder.layout.setVisibility(isExpand ? View.VISIBLE:View.GONE);
         if(isExpand=re.get(position).isExpand()==false) {
             holder.imageViewmax.setImageResource(R.drawable.ic_baseline_add_24);
         }
              else{
                 holder.imageViewmax.setImageResource(R.drawable.ic_baseline_minimize_24);
             }

        holder.typetest.setText(resultApi.getData().get(position).getNotes()+"");
        holder.recycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        holder.adapter=new AdapteronlyImage(mContext);
        holder.adapter.setlist(resultApi.getData().get(position).getFiles());
        holder.recycler.setAdapter(holder.adapter);
        Log.d(TAG, "onBindViewHolder: "+resultApi.getData().get(position).getFiles() );

    }

    @Override
    public int getItemCount() {
        if(resultApi.getData().size()>0)
            return resultApi.getData().size();
        else
            return 0;
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder
    {
        ImageView imageViewmax,imageViewmin;
        LinearLayout layout;
        RecyclerView recycler;
        AdapteronlyImage adapter;
        TextView typetest,date;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            imageViewmax=itemView.findViewById(R.id.plus_expandbal_list_result);
            layout=itemView.findViewById(R.id.body_expand_result_id);
            recycler = itemView.findViewById(R.id.recyclerview_image_inseid_result_id);
            typetest=itemView.findViewById(R.id.name_of_singl_rsult_id);
            imageViewmax.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Resultcopy resultcopy   = re.get(getAdapterPosition());
                    resultcopy.setExpand(!resultcopy.isExpand());

                    notifyItemChanged(getAdapterPosition());

                }
            });
        }
    }


}