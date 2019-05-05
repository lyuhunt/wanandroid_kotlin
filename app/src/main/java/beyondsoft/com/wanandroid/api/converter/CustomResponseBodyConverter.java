package beyondsoft.com.wanandroid.api.converter;

import android.util.Log;

import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 当前类注释：自定义ResponseBodyConverter
 * Author :LeonWang
 * Created  2016/10/11.11:33
 * Description:
 * E-mail:lijiawangjun@gmail.com
 */

final class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final TypeAdapter<T> adapter;

    CustomResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.e("TAG", "response 加密前  ================" + response);
        //解密
//      String data =
//        Log.d("TAG", "response  --------------------------" + data);
        return adapter.fromJson(response);
    }

}
