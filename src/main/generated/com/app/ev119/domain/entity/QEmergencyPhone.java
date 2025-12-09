package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEmergencyPhone is a Querydsl query type for EmergencyPhone
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmergencyPhone extends EntityPathBase<EmergencyPhone> {

    private static final long serialVersionUID = -281906530L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEmergencyPhone emergencyPhone = new QEmergencyPhone("emergencyPhone");

    public final StringPath emergencyPhoneName = createString("emergencyPhoneName");

    public final StringPath emergencyPhoneNumber = createString("emergencyPhoneNumber");

    public final StringPath emergencyPhoneRelationship = createString("emergencyPhoneRelationship");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QEmergencyPhone(String variable) {
        this(EmergencyPhone.class, forVariable(variable), INITS);
    }

    public QEmergencyPhone(Path<? extends EmergencyPhone> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEmergencyPhone(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEmergencyPhone(PathMetadata metadata, PathInits inits) {
        this(EmergencyPhone.class, metadata, inits);
    }

    public QEmergencyPhone(Class<? extends EmergencyPhone> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

