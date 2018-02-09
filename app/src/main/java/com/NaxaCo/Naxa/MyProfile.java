package com.NaxaCo.Naxa;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.NaxaCo.Naxa.Adapter.MyProfile_adapter;
import com.NaxaCo.Naxa.DbAccess.Blob.GetBitmap;
import com.NaxaCo.Naxa.DbAccess.Blob.GetBlob;
import com.NaxaCo.Naxa.DbAccess.Conversion_Area;
import com.NaxaCo.Naxa.DbAccess.Conversion_Area_Dto;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class MyProfile extends AppCompatActivity {
    Conversion_Area conversion_area;
    GetBlob IcGetBlob;
    GetBitmap IcGetBitmap;
    private RecyclerView recyclerView;
    private MyProfile_adapter IcMyProfile_adapter;
    private List<Conversion_Area_Dto> conversion_area_dtoList;
    Conversion_Area_Dto IcConversion_area_dto;

    String idValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        conversion_area = new Conversion_Area(this);
        IcGetBitmap=new GetBitmap();
        IcGetBlob=new GetBlob();
        recyclerView = findViewById(R.id.recycler_view);
        conversion_area_dtoList = new ArrayList<>();
        IcMyProfile_adapter = new MyProfile_adapter(this, conversion_area_dtoList);
        RecyclerView.LayoutManager OLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(OLayoutManager);
        recyclerView.setAdapter(IcMyProfile_adapter);
        retriveData();
    }

    public void retriveData() {

        conversion_area.getInfo();
        int[] covers = new int[]{
                R.drawable.back,
                R.drawable.back,
                R.drawable.back
        };
        //   conversion_area_dtoList.add(conversion_area.getInfo());
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


}
