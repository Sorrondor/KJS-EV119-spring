package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFirstAid is a Querydsl query type for FirstAid
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFirstAid extends EntityPathBase<FirstAid> {

    private static final long serialVersionUID = -1047687827L;

    public static final QFirstAid firstAid = new QFirstAid("firstAid");

    public final ListPath<FirstAidKeywords, QFirstAidKeywords> firstAidKeywords = this.<FirstAidKeywords, QFirstAidKeywords>createList("firstAidKeywords", FirstAidKeywords.class, QFirstAidKeywords.class, PathInits.DIRECT2);

    public final ListPath<FirstAidProcedures, QFirstAidProcedures> firstAidProcedures = this.<FirstAidProcedures, QFirstAidProcedures>createList("firstAidProcedures", FirstAidProcedures.class, QFirstAidProcedures.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<com.app.ev119.domain.type.UrgencyType> urgency = createEnum("urgency", com.app.ev119.domain.type.UrgencyType.class);

    public QFirstAid(String variable) {
        super(FirstAid.class, forVariable(variable));
    }

    public QFirstAid(Path<? extends FirstAid> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFirstAid(PathMetadata metadata) {
        super(FirstAid.class, metadata);
    }

}

