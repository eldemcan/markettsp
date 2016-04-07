package mp.producer.com.cfp.facade.converter;


public interface Converter<SOURCE, TARGET> {
    TARGET convert(SOURCE source);
    TARGET convert(SOURCE source, TARGET target);
}
