package com.statuswatchr.statuswatchr.service;

import com.statuswatchr.statuswatchr.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class HealthCheckService {

    public record CheckResult(Status status, String error, Integer httpCode){}

    public CheckResult check(String url){
        try{
            HttpURLConnection con = (HttpURLConnection) URI.create(url).toURL().openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(3000);
            con.setReadTimeout(3000);

            int code = con.getResponseCode();

            if(code>=200 && code <300){
                return new CheckResult(Status.UP, null, code);
            }
            return new CheckResult(Status.DOWN, "HTTP " + code, code);
        }
        catch (Exception e) {
            return new CheckResult(Status.DOWN, e.getClass().getSimpleName() + ":" + e.getMessage(),null);
        }
    }
}
