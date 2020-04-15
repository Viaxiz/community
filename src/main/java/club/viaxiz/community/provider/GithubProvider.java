package club.viaxiz.community.provider;

import club.viaxiz.community.dto.AccessTokenDTO;
import club.viaxiz.community.dto.GithubUser;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: xizhong
 * @Date: 2020-4-11 00:21
 */
@Component
public class GithubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        //try (Response response = client.newCall(request).execute()){  //这种写法也可以，没差。
        try {

            Response response = client.newCall(request).execute();

            String string = response.body().string();
            String strs[] = string.split("&");
            String accessTokenStr = strs[0];
            String str[] = accessTokenStr.split("=");
            String token = str[1];

//            System.out.println(token);

            return token;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                    .url("https://api.github.com/user?access_token="+accessToken)
                    .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string,GithubUser.class);
            return githubUser;
        } catch (IOException e) {
        }
        return  null;
    }

}
