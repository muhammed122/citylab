package com.medical.citylap.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.medical.citylap.R;
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

        if( resultApi.getData().get(position).getMediaType()==1)
        {
            //file
            holder.recycler.setVisibility(View.GONE);
            holder.noimag.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.recycler.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
            holder.adapter=new AdapteronlyImage(mContext);
            holder.adapter.setlist(resultApi.getData().get(position).getFiles());
            holder.recycler.setAdapter(holder.adapter);
            Log.d(TAG, "onBindViewHolder: "+resultApi.getData().get(position).getFiles() );
        }
        holder.typetest.setText(resultApi.getData().get(position).getNotes()+"");


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
        LinearLayout layout,linearLayout_pdf;
        RecyclerView recycler;
        AdapteronlyImage adapter;
        TextView typetest,date,noimag;

        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            imageViewmax=itemView.findViewById(R.id.plus_expandbal_list_result);
            layout=itemView.findViewById(R.id.body_expand_result_id);
            recycler = itemView.findViewById(R.id.recyclerview_image_inseid_result_id);
            typetest=itemView.findViewById(R.id.name_of_singl_rsult_id);
            noimag=itemView.findViewById(R.id.textnnoimage);
            linearLayout_pdf=itemView.findViewById(R.id.linear_download_pdf);

            linearLayout_pdf.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.R)
                @Override
                public void onClick(View v) {

//                    new DownloadPDF().
//                            execute("http://"+resultApi.getData().get(getAdapterPosition()).getFiles().get(0).toString());
//
//
//                    final Dialog dialog = new Dialog(mContext);
//                    dialog.setContentView(R.layout.custompdfview);       // Include dialog.xml file
//                    dialog.show();      // Include dialog.xml file
//                    PDFView PDF=dialog.findViewById(R.id.pdfView);
//

                }
            });

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