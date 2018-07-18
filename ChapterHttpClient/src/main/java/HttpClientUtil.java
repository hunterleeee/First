import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {
    public static void doget(String url, Map<String,String>param,CookieStore cookieStore) throws IOException {

        DefaultHttpClient client =new DefaultHttpClient();

        //设置cookie
        if (cookieStore!=null){
            client.setCookieStore(cookieStore);
        }
        //设置参数
        String getParams=null;
        if (param!=null){
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (String key:param.keySet()){
                list.add(new BasicNameValuePair(key, param.get(key)));
            }
            getParams = "?"+EntityUtils.toString(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        }
        // 执行
        HttpGet get = new HttpGet(url + getParams);
        HttpResponse response  = client.execute(get);
        //返回值处理
        String result =EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result : " + result);
        System.out.println("code : " + response.getStatusLine().getStatusCode());
    }

    public static void doPostJson(String url,Map<String,String>param,Map<String,String>headers,CookieStore cookieStore) throws IOException {
        //创建post方法对象
        DefaultHttpClient client =new DefaultHttpClient();
        HttpPost post =new HttpPost(url);
        //创建参数，并添加到方法中
        if (param!=null){
            JSONObject json =new JSONObject();
            for (String str :param.keySet()){
                json.put(str,param.get(str));
            }
            StringEntity entity = new StringEntity(json.toString(),"utf-8");
            post.setEntity(entity);
        }
        //设置请求头
        if (headers!=null){
            for (String str :headers.keySet()){
                post.setHeader(str,headers.get(str));
            }
        }
        //cookie设置
        if (cookieStore!=null){
            client.setCookieStore(cookieStore);
        }
        //执行并打印结果
        HttpResponse response = client.execute(post);
        int code = response.getStatusLine().getStatusCode();
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("resulet : " +result);
        System.out.println("code : "+code);
    }
    public static void doPostForms(String url,Map<String,String>param,Map<String,String>headers,CookieStore cookieStore) throws IOException {

        DefaultHttpClient client= new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        //设置请求头
        if (headers!=null){
            for (String str :headers.keySet()){
                post.setHeader(str,headers.get(str));
            }
        }
        //cookie设置
        if (cookieStore!=null){
            client.setCookieStore(cookieStore);
        }
        //form表单设置
        if (param!=null){
            List<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();
            for (String str:param.keySet()){
                nameValuePairs.add(new BasicNameValuePair(str,param.get(str)));
            }
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        }
        //执行显示结果
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    public static void main(String[]args) throws IOException {
        CookieStore myCookieStore =new BasicCookieStore();
        final BasicClientCookie basicClientCookie= new BasicClientCookie("login","true");
        basicClientCookie.setDomain("10.4.1.119");
        basicClientCookie.setPath("/");
        myCookieStore.addCookie(basicClientCookie);

        Map<String,String> param =new HashMap<String, String>();
        param.put("name","zhangsan");
        param.put("age","18");

        Map<String,String> head =new HashMap<String, String>();
        head.put("content-type","application/json");

        HttpClientUtil.doget("http://10.4.1.119:9999/get/responsecookies",param,null);
        HttpClientUtil.doget("http://10.4.1.119:9999/get/withcookies",param,myCookieStore);
        HttpClientUtil.doPostJson("http://10.4.1.119:9999/postjson/withcookies",param,head,myCookieStore);
        HttpClientUtil.doPostForms("http://10.4.1.119:9999/postform/withcookies",param,head,myCookieStore);

    }
}
