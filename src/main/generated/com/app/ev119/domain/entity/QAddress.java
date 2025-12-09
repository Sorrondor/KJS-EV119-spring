package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = -609559853L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final DateTimePath<java.util.Date> addressCreateAt = createDateTime("addressCreateAt", java.util.Date.class);

    public final StringPath addressLatitude = createString("addressLatitude");

    public final StringPath addressLongitude = createString("addressLongitude");

    public final StringPath addressRoad = createString("addressRoad");

    public final StringPath addressStreet = createString("addressStreet");

    public final EnumPath<com.app.ev119.domain.type.AddressType> addressType = createEnum("addressType", com.app.ev119.domain.type.AddressType.class);

    public final StringPath addressZipcode = createString("addressZipcode");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

