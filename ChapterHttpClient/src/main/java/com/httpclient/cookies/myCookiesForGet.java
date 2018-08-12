package com.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class myCookiesForGet {
    private String url ;
    private ResourceBundle bundle;

    private CookieStore cookieStore;

    @BeforeTest
    public void beforeTest(){
        bundle = ResourceBundle.getBundle("application",Locale.CHINA);
        url= bundle.getString("test.url");
    }
    @Test
    public void testGetCookies() throws IOException {
        String result;
        String testURL =this.url+bundle.getString("getCookies.url");

        HttpGet get = new HttpGet(testURL);
        DefaultHttpClient httpClient =new DefaultHttpClient();
        HttpResponse httpResponse = httpClient.execute(get);
        result =EntityUtils.toString(httpResponse.getEntity());
        System.out.println(result);

        this.cookieStore = httpClient.getCookieStore();
        List<Cookie> cookieslist =cookieStore.getCookies();

        for (Cookie cookie:cookieslist ){
            String name =cookie.getName();
            String value =cookie.getValue();
            System.out.println(name +"\t"+value);
        }
    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testGetByCookies() throws IOException {
        String result = null;
        String testURL =this.url+bundle.getString("test.get.withcookies");

        HttpGet get =new HttpGet(testURL);
        DefaultHttpClient client =new DefaultHttpClient();
        client.setCookieStore(this.cookieStore);
        HttpResponse httpResponse = client.execute(get);
        if (httpResponse.getStatusLine().getStatusCode()==200){
            result = EntityUtils.toString(httpResponse.getEntity());
        }
        System.out.println(result);
    }
}
