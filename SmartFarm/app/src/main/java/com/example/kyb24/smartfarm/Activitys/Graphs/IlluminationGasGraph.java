package com.example.kyb24.smartfarm.Activitys.Graphs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.kyb24.smartfarm.R;
import com.example.kyb24.smartfarm.util.Util;
import com.handstudio.android.hzgrapherlib.animation.GraphAnimation;
import com.handstudio.android.hzgrapherlib.graphview.LineGraphView;
import com.handstudio.android.hzgrapherlib.vo.GraphNameBox;
import com.handstudio.android.hzgrapherlib.vo.linegraph.LineGraph;
import com.handstudio.android.hzgrapherlib.vo.linegraph.LineGraphVO;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class IlluminationGasGraph extends AppCompatActivity {

    private ViewGroup layoutGraphView;

    HttpPost httppost;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;

    String[] timeStamp;
    float[] air, light;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illumination_gas_graph);
        layoutGraphView = (ViewGroup) findViewById(R.id.layoutIlluminationGraphView);
        DrawGraph();
        //Toast.makeText(getApplicationContext(), "light", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(getApplicationContext(), "light", Toast.LENGTH_SHORT).show();
        //DrawGraph();
        //setLineGraph();
    }

     void DrawGraph(){
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost(Util.serverAddress + "/returnCollectedSensorValue.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            JSONArray jsonArray = new JSONArray(response);
            //JSONObject json = new JSONObject(response);

            timeStamp       = new String[jsonArray.length()];
            air             = new float[jsonArray.length()];
            light           = new float[jsonArray.length()];
            //Toast.makeText(getApplicationContext(), "test2", Toast.LENGTH_SHORT).show();
            //GRAPH SETTING
            for(int i=0; i<jsonArray.length(); i++){
                timeStamp[i] = jsonArray.getJSONObject(i).getString("time");
                light[i] = jsonArray.getJSONObject(i).getInt("cdn");
                air[i] = jsonArray.getJSONObject(i).getInt("gas");
            }
            //tvCurLight.setText(json.getString("light"));
            //Toast.makeText(getApplicationContext(), json.getString("temperature"), Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {}

        setLineGraph();
    }
    private void setLineGraph() {
        //all setting
        LineGraphVO vo = makeLineGraphAllSetting();

        //default setting
        //LineGraphVO vo = makeLineGraphDefaultSetting();

        layoutGraphView.addView(new LineGraphView(this, vo));
    }

    /**
     * make simple line graph
     * @return
     */
    private LineGraphVO makeLineGraphDefaultSetting() {

        String[] legendArr 	= {"1","2","3","4","5"};
        float[] graph1 		= {500,100,300,200,100};
        float[] graph2 		= {000,100,200,100,200};
        float[] graph3 		= {200,500,300,400,000};

        List<LineGraph> arrGraph 		= new ArrayList<LineGraph>();
        arrGraph.add(new LineGraph("android", 0xaa66ff33, graph1));
        arrGraph.add(new LineGraph("ios", 0xaa00ffff, graph2));
        arrGraph.add(new LineGraph("tizen", 0xaaff0066, graph3));

        LineGraphVO vo = new LineGraphVO(legendArr, arrGraph);
        return vo;
    }

    /**
     * make line graph using options
     * @return
     */
    private LineGraphVO makeLineGraphAllSetting() {
        //BASIC LAYOUT SETTING
        //padding
        int paddingBottom 	= LineGraphVO.DEFAULT_PADDING;
        int paddingTop 		= LineGraphVO.DEFAULT_PADDING;
        int paddingLeft 	= LineGraphVO.DEFAULT_PADDING;
        int paddingRight 	= LineGraphVO.DEFAULT_PADDING;

        //graph margin
        int marginTop 		= LineGraphVO.DEFAULT_MARGIN_TOP;
        int marginRight 	= LineGraphVO.DEFAULT_MARGIN_RIGHT;

        //max value
        //int maxValue 		= LineGraphVO.DEFAULT_MAX_VALUE;
        //int maxValue 		= 100;
        int maxValue 		= 900;

        //increment
        //int increment 		= LineGraphVO.DEFAULT_INCREMENT;
        //int increment 		    = 10;
        int increment 		    = 200;



        List<LineGraph> arrGraph = new ArrayList<LineGraph>();

        arrGraph.add(new LineGraph("조도", 0xaaff7f50, light /*, R.drawable.water_icon*/));
        arrGraph.add(new LineGraph("가스", 0xaa228b22, air/*, R.drawable.water_icon*/));

        LineGraphVO vo = new LineGraphVO(
                paddingBottom, paddingTop, paddingLeft, paddingRight,
                marginTop, marginRight, maxValue, increment, timeStamp, arrGraph);

        //set animation
        vo.setAnimation(new GraphAnimation(GraphAnimation.LINEAR_ANIMATION, GraphAnimation.DEFAULT_DURATION));
        // change GraphNameBox properties
        GraphNameBox graphNameBox = new GraphNameBox();
        graphNameBox.setNameboxTextSize(50);

        //set graph name box
        vo.setGraphNameBox(graphNameBox);
        //set draw graph region
//		vo.setDrawRegion(true);

        //use icon
//		arrGraph.add(new Graph(0xaa66ff33, graph1, R.drawable.icon1));
//		arrGraph.add(new Graph(0xaa00ffff, graph2, R.drawable.icon2));
//		arrGraph.add(new Graph(0xaaff0066, graph3, R.drawable.icon3));

//		LineGraphVO vo = new LineGraphVO(
//				paddingBottom, paddingTop, paddingLeft, paddingRight,
//				marginTop, marginRight, maxValue, increment, legendArr, arrGraph, R.drawable.bg);
        return vo;
    }
}
