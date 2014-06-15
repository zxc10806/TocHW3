import org.json.*;
import java.net.*;
import java.io.*;
public class TocHw3{
                JSONObject test;
                //arg[0]:url
                //arg[1]¶mÂ//arg[2]:¤g¦a°Ϭq¦ì©Ϋﺕ«°Ϫù    //arg[3]:¥æ¦~¤ë
                public static  void main(String [] args)
                {
                                try{

                                                String sURL = new String(args[0]);
                                                // Connect to the URL using java's native library
                                                URL url = new URL(sURL);
                                                String area = new String(args[1]);
                                                String loc = new String(args[2]);
                                                int year = Integer.valueOf(args[3]);
                                                System.out.println(area+","+loc+","+year);
                                                HttpURLConnection request = (HttpURLConnection) url.openConnection();
                                                request.setRequestProperty("Accept-Charset", "UTF-8");
                                                request.connect();
                                                JSONTokener jt = new JSONTokener(new InputStreamReader((InputStream)request.getContent()));
                                                JSONArray rootobj = new JSONArray(jt);
                                                int totalp = 0;
                                                int totaln = 0;
                                                for(int i = 0;i<rootobj.length();i++)
                                                {
                                                        JSONObject j = rootobj.getJSONObject(i);
                                                        String jarea = j.getString("鄉鎮市區");
                                                        String jloc = j.getString("土地區段位置或建物區門牌");
                                                        if(jarea.contains(area)&&jloc.contains(loc))
                                                        {
                                                                int jy = j.getInt("交易年月");
                                                                jy/=100;

                                                                if(jy>=year)
                                                                {
                                                                        totaln++;
                                                                        totalp+=j.getInt("總價元");
                                                                }
                                                        }
                                                }
                                                System.out.println(totalp/totaln);
                                }
                                catch(Exception e)
                                {
                                                System.out.println(e.getMessage());
                                }
                }
}
