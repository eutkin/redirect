package net.eutkin.redirect.service.crud;

import lombok.val;
import net.eutkin.redirect.entity.DestParam;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.entity.Source;
import net.eutkin.redirect.repository.DestParamRepository;
import net.eutkin.redirect.repository.ParameterRepository;
import net.eutkin.redirect.repository.SourcesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
public class CrudServiceImpl implements CrudService {

    @Autowired
    private SourcesRepository sourcesRepository;
    @Autowired
    private ParameterRepository parameterRepository;
    @Autowired
    private DestParamRepository destParamRepository;

    @Override
    @Transactional
    public void saveSource(Source source) {
        Source savedSource = sourcesRepository.save(source);
        for (val destination : savedSource.getDestinations()) {
            List<Parameter> parameters = parameterRepository.findAllByDomain(destination.getDomain());
            for (val parameter : parameters) {
                DestParam destParam = new DestParam()
                        .setDestId(destination.getId())
                        .setDomain(destination.getDomain())
                        .setSrcName(parameter.getSrcName())
                        .setId(UUID.randomUUID());
                destParamRepository.save(destParam);
            }
        }
    }

    @Transactional
    @Override
    public void saveParameters(List<Parameter> parameters) {
        parameterRepository.save(parameters);

    }
}
