package mp.producer.com.cfp.facade.converter;

/**
 * Created by i308760 on 30/03/2016.
 */
public interface Converter<SOURCE, TARGET> {
    TARGET convert(SOURCE source);
    TARGET convert(SOURCE source, TARGET target);
}
