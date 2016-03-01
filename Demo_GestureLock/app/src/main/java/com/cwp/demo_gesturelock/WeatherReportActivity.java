package com.cwp.demo_gesturelock;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WeatherReportActivity extends AppCompatActivity {

    private TextView locationTxt,temperationTxt,weatherTxt,tempRangeTxt,windDirectionTv,
            humidityTv,updateTimeTv;
    private ImageView daylightIcon,nightIcon;
    private EditText location_get;
    private Button searchBtn;
    private String  cityName=null,
            cityId=null,
            currentTemp=null,
            maxTemp=null,
            minTemp=null,
            weather=null,
            imgDaylight=null,
            imgNight=null,
            updateTime=null,
            humidity=null,
            windDirection=null,
            windScore=null;
    DBUtils dbUtils;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_report);
        //绑定ID
        locationTxt= (TextView) findViewById(R.id.location_display);
        temperationTxt= (TextView) findViewById(R.id.temperature_display);
        weatherTxt= (TextView) findViewById(R.id.weatherTv);
        tempRangeTxt = (TextView) findViewById(R.id.tempRange);
        humidityTv= (TextView) findViewById(R.id.humidityTv);
        windDirectionTv= (TextView) findViewById(R.id.windDirectionTv);
        updateTimeTv= (TextView) findViewById(R.id.updateTimeTv);
        daylightIcon= (ImageView) findViewById(R.id.daylight_icon);
        nightIcon= (ImageView) findViewById(R.id.night_icon);
        location_get= (EditText) findViewById(R.id.locationEdt);
        searchBtn= (Button) findViewById(R.id.searchBtn);

        dbUtils=new DBUtils(this);


        //下面这些是用来测试布局文件写的是否正确
        /*locationTxt.setText("南京");
        temperationTxt.setText("21°C");
        fullMessageTxt.setText("中雨\n21~25摄氏度\n北风3级\n适度81%\n今天23：40发布");
        daylightIcon.setImageResource(R.drawable.icon);
        nightIcon.setImageResource(R.drawable.icon);*/


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2015/10/23
                cityName=String.valueOf(location_get.getText());
                cityId=dbUtils.queryCityId(cityName);
                String url1="http://www.weather.com.cn/data/cityinfo/"+cityId+".html";
                String url2="http://www.weather.com.cn/data/sk/"+cityId+".html";
                getFromHttp_url1(url1);
                getFromHttp_url2(url2);
            }
        });
    }

    public void getFromHttp_url1(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String result=null;
                StringBuilder builder = new StringBuilder();
                try {
                    URL urlString = new URL(params[0]);
                    URLConnection connection = urlString.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    //// TODO: 2015/10/24
                    //从url中获取数据
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        //此处的builder就是我们从网上获得的信息
                        //待会儿对builder进行解析，就能得到我们需要的信息
                        builder.append(line);
                    }
                    result=builder.toString();
                    //result就是网上获得的json格式
                    //下面我们对它进行解析：
                    Log.i("!","the first result is :"+result);
//                    jsonToValue(result,"weatherinfo");
//                    jsonToValue(result,"weatherinfo");
                    JSONObject json=new JSONObject(result).getJSONObject("weatherinfo");
                    //cityName=json.optString("city");
                    //cityId=json.optString("cityid");
                    //currentTemp= json.optString("temp");
                    minTemp= json.optString("temp2");
                    maxTemp=json.optString("temp1");
                    weather=json.optString("weather");
                    //updateTime=json.optString("time");
                    //humidity=json.optString("SD");
                    //windDirection=json.optString("WD");
                    //windScore=json.optString("WS");
                    Log.i("!", "这段代码显示出来了");
                    Log.i("!",">>>>>>>"+cityName+cityId+currentTemp+minTemp+maxTemp+weather+
                            updateTime+humidity+windDirection+windScore);
                    //close operation
                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }

            protected void onPostExecute(String result) {
                Log.i("!","the second result is : "+result);
                locationTxt.setText(cityName);
                //temperationTxt.setText(currentTemp+"℃");
                weatherTxt.setText("天气 :"+weather);
                tempRangeTxt.setText("温度 :"+minTemp+"~"+maxTemp);
                //windDirectionTv.setText("风向 :"+windDirection+"风力 :"+windScore);
                //humidityTv.setText("湿度 :"+humidity);
                //updateTimeTv.setText("今天"+updateTime+"发布");
                super.onPostExecute(result);
            }
        }.execute(url);
    }
    public void getFromHttp_url2(String url) {
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... params) {
                String result=null;
                StringBuilder builder = new StringBuilder();
                try {
                    URL urlString = new URL(params[0]);
                    URLConnection connection = urlString.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    //// TODO: 2015/10/24
                    //从url中获取数据
                    String line = bufferedReader.readLine();
                    if (line != null) {
                        //此处的builder就是我们从网上获得的信息
                        //待会儿对builder进行解析，就能得到我们需要的信息
                        builder.append(line);
                    }
                    result=builder.toString();
                    //result就是网上获得的json格式
                    //下面我们对它进行解析：
                    Log.i("!","the first result is :"+result);
//                    jsonToValue(result,"weatherinfo");
//                    jsonToValue(result,"weatherinfo");
                    JSONObject json=new JSONObject(result).getJSONObject("weatherinfo");
                    //cityName=json.optString("city");
                    //cityId=json.optString("cityid");
                    currentTemp= json.optString("temp");
                    //minTemp= json.optString("temp2");
                    //maxTemp=json.optString("temp1");
                    //weather=json.optString("weather");
                    updateTime=json.optString("time");
                    humidity=json.optString("SD");
                    windDirection=json.optString("WD");
                    windScore=json.optString("WS");
                    Log.i("!", "这段代码显示出来了");
                    Log.i("!",">>>>>>>"+cityName+cityId+currentTemp+minTemp+maxTemp+weather+
                            updateTime+humidity+windDirection+windScore);
                    //close operation

                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return result;
            }

            protected void onPostExecute(String result) {
                Log.i("!","the second result is : "+result);
                //locationTxt.setText(cityName);
                temperationTxt.setText(currentTemp+"℃");
                //weatherTxt.setText("天气 :"+weather);
                //tempRangeTxt.setText("温度 :"+minTemp+"℃"+"~"+maxTemp+"℃");
                windDirectionTv.setText("风向 :"+windDirection+"风力 :"+windScore);
                humidityTv.setText("湿度 :"+humidity);
                updateTimeTv.setText("今天"+updateTime+"发布");
                super.onPostExecute(result);
            }
        }.execute(url);
    }
}