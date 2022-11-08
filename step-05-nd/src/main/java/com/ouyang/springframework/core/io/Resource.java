package com.ouyang.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;


/**
 * 整合成为的资源对象，作用是从中能得到io输入流
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
