package at.telvla.cricket;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.List;

public class NewsAdapter extends ArrayAdapter<NewsInfo> {

    List<NewsInfo> contactList;
    Context context;
    String link_img;
    Boolean Res_file_save;
    String mFileName = "file_id";
    String Num;
    Integer int_id_current;
    Point size;
    Integer width;
    String CountView;

    public NewsAdapter(Context context, List<NewsInfo> list) {
        super(context, 0, list);
        this.context = context;
        contactList = list;
    }

    public Object getId(int position) {
        return this.contactList.get(position).getId();
    }

    String getNewsId(int position){
        return ((String) getId(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linelistview, parent, false);
        TextView textViewId = (TextView) rowView.findViewById(R.id.id_list);
        TextView textViewTitle = (TextView) rowView.findViewById(R.id.title);
        TextView textViewAbstr = (TextView) rowView.findViewById(R.id.abstr);
        ImageView ImageViewImg = (ImageView) rowView.findViewById(R.id.img);
        TextView textViewDate = (TextView) rowView.findViewById(R.id.create_date);
        TextView textViewView = (TextView) rowView.findViewById(R.id.count_view);

        Num = new File().File_Read(context, mFileName);

        if(Num != null){
            int_id_current = Integer.parseInt(Num);
            if(Integer.parseInt(contactList.get(position).getId()) > int_id_current){
                Res_file_save = new File().File_Entry(context, mFileName, contactList.get(position).getId());
            }
        }else{
            Res_file_save = new File().File_Entry(context, mFileName, contactList.get(position).getId());
        }

        textViewId.setText(contactList.get(position).getId());
        textViewTitle.setText(contactList.get(position).getTitle());
        textViewAbstr.setText(contactList.get(position).getAbstr());
        textViewDate.setText("date: " + contactList.get(position).getDate());
        textViewView.setText("view: " + contactList.get(position).getCountView());

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        link_img = contactList.get(position).getImg();

        if (link_img.length() == 0) {
            link_img = "file:///android_asset/cricket_img_1.jpg";
        }

        Picasso.with(context)
                .load(link_img)
                .resize(width, 800)
                .centerCrop()
                .error(R.drawable.drawer_shadow)
                .into(ImageViewImg);

        return rowView;
    }
}
