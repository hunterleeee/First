package com.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class myCookiesForPost {
    private String url ;
    private ResourceBundle bundle;

    private CookieStore myCookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url= bundle.getString("test.url");
    }

    /**
     * 调用该方法可以拿到cookes信息 并对cookie是信息做修改
     * @throws IOException
     */
    @Test
    public void testGetCookies() throws IOException {
        String result;
        String testURL =this.url+bundle.getString("getCookies.url");

        HttpGet get = new HttpGet(testURL);
        DefaultHttpClient httpClient =new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(get);
        result =EntityUtils.toString(httpResponse.getEntity());
        //System.out.println(result);
        //拿到cookie信息 将cookie信息获取到，并放入新的cookie容器中一共个下面接口使用 此处对路径做了处理 用的是 "/" 而不是 /get
        myCookieStore =new BasicCookieStore();
        CookieStore cookieStore = httpClient.getCookieStore();
        List<Cookie> cookieslist =cookieStore.getCookies();
        for (Cookie cookie:cookieslist ){
            String name =cookie.getName();
            String value =cookie.getValue();
            System.out.println(name +"\t"+value+"\t"+cookie.getDomain()+"\t"+cookie.getPath());

            final  BasicClientCookie basicClientCookie= new BasicClientCookie(name,value);
            basicClientCookie.setDomain(cookie.getDomain());
            basicClientCookie.setPath("/");
            myCookieStore.addCookie(basicClientCookie);
        }
        for (Cookie cookie:myCookieStore.getCookies()){
            System.out.println(cookie.getPath());
            System.out.println(cookie.getDomain());
            System.out.println(cookie.getName());
            System.out.println(cookie.getValue());
        }
    }

    /**
     * get方法+cookie + 参数 方法1
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetMethod() throws IOException {
        String testURL = this.url+bundle.getString("test.cookieparam");
        DefaultHttpClient client =new DefaultHttpClient();

        //设置cookie
        client.setCookieStore(this.myCookieStore);

        //设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("name", "zhangsan"));
        list.add(new BasicNameValuePair("age", "18"));
        String getParams = EntityUtils.toString(new UrlEncodedFormEntity(list, HTTP.UTF_8));
        // 执行
        HttpGet get = new HttpGet(testURL + "?" + getParams);
        HttpResponse  response  = client.execute(get);
        //返回值处理
        String result =EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result : " + result);
    }


    /**
     * post 方法+ json参数+cookie+header
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostByCookiesWithJson() throws IOException {
        String result;
        String testURL =this.url+bundle.getString("test.postjson.withcookies");
       //创建post方法对象
        DefaultHttpClient client =new DefaultHttpClient();
        HttpPost post =new HttpPost(testURL);

        //创建参数，并添加到方法中
        JSONObject param =new JSONObject();
        param.put("name","zhangsan");
        param.put("age","18");
        //设置请求头
        post.setHeader("content-type","application/json");

        StringEntity entity = new StringEntity(param.toString(),"utf-8");
        post.setEntity(entity);
        //cookie设置
        client.setCookieStore(this.myCookieStore);
        //执行并打印结果
        HttpResponse response = client.execute(post);
        int code = response.getStatusLine().getStatusCode();
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("resulet : " +result);
        System.out.println("code : "+code);
        //
        JSONObject jsonresult = new JSONObject(result);
        String res = (String) jsonresult.get("res");
        String status = (String) jsonresult.get("code");

        Assert.assertEquals("this response is postjson method with cookies",res);
        Assert.assertEquals("1",status);
    }

    /**
     * post 方法+ form表单+cookie+header
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostByCookiesWithforms() throws IOException {

        String testURl = this.url+ bundle.getString("test.postform.withcookies");
        DefaultHttpClient client= new DefaultHttpClient();
        HttpPost post = new HttpPost(testURl);
        //设置请求头
        post.setHeader("content-type","application/x-www-form-urlencoded");
        //设置cookies
        client.setCookieStore(myCookieStore);
        //form表单设置
        List<NameValuePair> nameValuePairs =new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("name","zhangsan"));
        nameValuePairs.add(new BasicNameValuePair("age","18"));
        post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        //执行显示结果
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
    }

    /**
     * post 方法+ form表单+cookie+header 方法2 失败调不同 不知道 BasicHttpParams 和 client.setParams(params); 是干什么的 不好用
     * @throws IOException
     */
    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostByCookiesWithforms2() throws IOException {

        String testURl = this.url+ bundle.getString("test.postform.withcookies");
        DefaultHttpClient client= new DefaultHttpClient();
        HttpPost post = new HttpPost(testURl);
        //设置请求头
        post.setHeader("content-type","application/json");
        //设置cookies
        client.setCookieStore(myCookieStore);
        //form表单设置
        BasicHttpParams params =new BasicHttpParams();
        params.setParameter("name","zhangsan");
        params.setParameter("age","18");
        client.setParams(params);
        //执行显示结果
        HttpResponse response = client.execute(post);
        String result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println("result : "+result);
    }

}
