package at.telvla.cricket;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SimpleAdapter extends ArrayAdapter<String> {
    private final Context context;


    List<String> array_id = new ArrayList<String>();
    List<String> array_abstr = new ArrayList<String>();
    List<String> array_img = new ArrayList<String>();
    List<String> array_date = new ArrayList<String>();
    List<String> array_view = new ArrayList<String>();
    List<String> array_title = new ArrayList<String>();


    String link_img;
    Boolean Res_file_save;
    String mFileName = "file_id";
    String Num;
    Integer int_id_current;
    Point size;
    Integer width;


    public SimpleAdapter (Context context, List<String> array_id, List<String> array_title, List<String> array_abstr, List<String> array_img, List<String> array_date, List<String> array_view) {
        super(context, R.layout.linelistview, array_title);
        this.context = context;
        this.array_id = array_id;
        this.array_title = array_title;
        this.array_abstr = array_abstr;
        this.array_img = array_img;
        this.array_date = array_date;
        this.array_view = array_view;
    }

    public Object getId(int position) {
        return this.array_id.get(position);
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
            if(Integer.parseInt(array_id.get(position)) > int_id_current){
                Res_file_save = new File().File_Entry(context, mFileName, array_id.get(position));
            }
        }else{
            Res_file_save = new File().File_Entry(context, mFileName, array_id.get(position));
        }

        textViewId.setText(array_id.get(position));
        textViewTitle.setText(array_title.get(position));
        textViewAbstr.setText(array_abstr.get(position));
        textViewDate.setText("date: " + array_date.get(position));
        textViewView.setText("view: " + array_view.get(position));

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        width = metrics.widthPixels;

        link_img = array_img.get(position);

        if (link_img.length() == 0) {
            link_img = "file:///android_asset/cricket_img_1.jpg";
        }

        Picasso.with(context)
                .load(link_img)
                .resize(width, 1500)
                .centerCrop()
                .error(R.drawable.drawer_shadow)
                .into(ImageViewImg);

        /*Picasso.with(context)
                .load(link_img)
                .resize(width, 450)
                .centerCrop()
                .error(R.drawable.drawer_shadow)
                .into(ImageViewImg);
        */
        return rowView;
    }
}