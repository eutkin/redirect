package net.eutkin.redirect.service.crud;

import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.Source;

import java.util.List;

public interface CrudService {

    void saveSource(Source source);

    void saveParameters(List<Parameter> parameters);

}
