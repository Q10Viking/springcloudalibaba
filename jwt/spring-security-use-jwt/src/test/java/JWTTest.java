import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JWTTest {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJoenoiLCJpYXQiOjE2Nzg4NjMxNzQsImV4cCI6MTY3ODg2MzIzNCwicm9sZXMiOiJhZG1pbiIsImxvZ28iOiJ4eHguanBnIn0.vgpt0JT9R3cioTCH5bjRrhJgOff28kKooChZrRKLGOo";
        Claims claims = Jwts.parser()
                .setSigningKey("123123")
                .parseClaimsJws(token)
                .getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("subject:"+claims.getSubject());
        System.out.println("issuedAt:"+claims.getIssuedAt());

        DateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("签发时间:"+sf.format(claims.getIssuedAt()));
        System.out.println("过期时间:"+sf.format(claims.getExpiration()));
        System.out.println("当前时间:"+sf.format(new Date()));

        System.out.println("roles:"+claims.get("roles"));
        System.out.println("logo:"+claims.get("logo"));
    }
}
