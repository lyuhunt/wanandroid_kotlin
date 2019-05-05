package beyondsoft.com.wanandroid.api.converter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * 当前类注释：自定义RequestBodyConverter
 * Author :LeonWang
 * Created  2016/10/11.11:30
 * Description:
 * E-mail:lijiawangjun@gmail.com
 */

public  class CustomRequestBodyConverter <T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        Log.e("TAG","request 加密前 ======================= " + value.toString());
        //加密
//        String data =
//        LogUtils.e("request 加密后 ======================= " + data);
        return RequestBody.create(MEDIA_TYPE, value.toString());

//        Buffer buffer = new Buffer();
//        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
//        JsonWriter jsonWriter = gson.newJsonWriter(writer);
//        adapter.write(jsonWriter, value);
//        jsonWriter.close();
//        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}