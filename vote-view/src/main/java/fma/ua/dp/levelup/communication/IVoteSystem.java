package fma.ua.dp.levelup.communication;

/**
 * Created by Admin on 13.10.2014.
 */
public interface IVoteSystem {
    String sendGet(String url) throws Exception;
    String sendPost(String url, String data)  throws Exception;
}
