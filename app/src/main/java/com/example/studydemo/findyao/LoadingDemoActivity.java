//package com.example.studydemo.findyao;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.studydemo.R;
//import com.wang.avi.AVLoadingIndicatorView;
//
//
//public class LoadingDemoActivity extends AppCompatActivity {
//
//    private RecyclerView mRecycler;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_loading_demo);
//
//        mRecycler = (RecyclerView) findViewById(R.id.recycler);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
//        mRecycler.setLayoutManager(layoutManager);
//        mRecycler.setAdapter(new RecyclerView.Adapter<IndicatorHolder>() {
//            @Override
//            public IndicatorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View itemView = getLayoutInflater().inflate(R.layout.item_indicator, parent, false);
//                return new IndicatorHolder(itemView);
//            }
//
//            @Override
//            public void onBindViewHolder(IndicatorHolder holder, final int position) {
//                holder.indicatorView.setIndicator(INDICATORS[position]);
//                holder.itemLayout.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(LoadingDemoActivity.this, IndicatorActivity.class);
//                        intent.putExtra("indicator", INDICATORS[position]);
//                        startActivity(intent);
//                    }
//                });
//            }
//
//            @Override
//            public int getItemCount() {
//                return INDICATORS.length;
//            }
//        });
//    }
//
//    final static class IndicatorHolder extends RecyclerView.ViewHolder {
//
//        public AVLoadingIndicatorView indicatorView;
//        public View itemLayout;
//
//        public IndicatorHolder(View itemView) {
//            super(itemView);
//            itemLayout = itemView.findViewById(R.id.itemLayout);
//            indicatorView = (AVLoadingIndicatorView) itemView.findViewById(R.id.indicator);
//        }
//    }
//
//
//    private static final String[] INDICATORS = new String[]{
//            "BallPulseIndicator",
//            "BallGridPulseIndicator",
//            "BallClipRotateIndicator",
//            "BallClipRotatePulseIndicator",
//            "SquareSpinIndicator",
//            "BallClipRotateMultipleIndicator",
//            "BallPulseRiseIndicator",
//            "BallRotateIndicator",
//            "CubeTransitionIndicator",
//            "BallZigZagIndicator",
//            "BallZigZagDeflectIndicator",
//            "BallTrianglePathIndicator",
//            "BallScaleIndicator",
//            "LineScaleIndicator",
//            "LineScalePartyIndicator",
//            "BallScaleMultipleIndicator",
//            "BallPulseSyncIndicator",
//            "BallBeatIndicator",
//            "LineScalePulseOutIndicator",
//            "LineScalePulseOutRapidIndicator",
//            "BallScaleRippleIndicator",
//            "BallScaleRippleMultipleIndicator",
//            "BallSpinFadeLoaderIndicator",
//            "LineSpinFadeLoaderIndicator",
//            "TriangleSkewSpinIndicator",
//            "PacmanIndicator",
//            "BallGridBeatIndicator",
//            "SemiCircleSpinIndicator",
//            "com.wang.avi.sample.MyCustomIndicator"
//    };
//
//}
