package vn.test.hub.product.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.test.hub.core.exception.BadRequestException;
import vn.test.hub.core.exception.NotFoundException;
import vn.test.hub.core.pagination.PaginationMapper;
import vn.test.hub.core.pagination.PaginationRequest;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.core.searching.Operators;
import vn.test.hub.product.constant.SystemConstant;
import vn.test.hub.product.domain.Attribute;
import vn.test.hub.product.repository.IAttributeRepository;
import vn.test.hub.product.service.IAttributeService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttributeServiceImpl implements IAttributeService {
    private static final String ATTRIBUTE = "Attribute";

    private final IAttributeRepository attributeRepository;

    @Override
    public Page<Attribute> findAll(Attribute attribute, PaginationRequest pageRequest) {
        List<FilterParam> filterParams = new ArrayList<>();
        if (attribute.getName() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName("name")
                    .value(attribute.getName())
                    .operator(Operators.LIKE).build());
        }

        if (attribute.getUpdatedBy() != null) {
            filterParams.add(FilterParam.builder()
                    .fieldName("created_by")
                    .value(attribute.getName())
                    .operator(Operators.EQ).build());
        }

        boolean deleted = attribute.getDeleted() != null && attribute.getDeleted();
        filterParams.add(
                FilterParam.builder()
                        .fieldName("deleted")
                        .value(deleted)
                        .operator(Operators.EQ).build()
        );

        List<String> validSortColumns = Arrays.asList("name", "created_at");
        Pageable pageable = PaginationMapper.toPageable(pageRequest, validSortColumns);

        return attributeRepository.findAll(filterParams, pageable);
    }

    @Override
    public Attribute findById(String id) {
        Attribute attribute = attributeRepository.findById(id);
        if (attribute == null)
            throw new NotFoundException(String.format(SystemConstant.NOT_FOUND, ATTRIBUTE, id), 4001);
        return attribute;
    }

    @Override
    public Attribute save(Attribute attribute) {
        if (attribute.getId() != null) {
            throw new BadRequestException("Bad Request", 5001);
        } else {
            return attributeRepository.save(attribute);
        }

    }

    @Override
    public Attribute update(Attribute attribute) {
        return null;
    }

    @Override
    public Attribute delete(String id) {
        return null;
    }
}
