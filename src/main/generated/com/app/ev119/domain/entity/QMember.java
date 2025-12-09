package com.app.ev119.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 1156346843L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final ListPath<Address, QAddress> addresses = this.<Address, QAddress>createList("addresses", Address.class, QAddress.class, PathInits.DIRECT2);

    public final ListPath<Allergy, QAllergy> allergies = this.<Allergy, QAllergy>createList("allergies", Allergy.class, QAllergy.class, PathInits.DIRECT2);

    public final ListPath<EmergencyPhone, QEmergencyPhone> emergencyPhones = this.<EmergencyPhone, QEmergencyPhone>createList("emergencyPhones", EmergencyPhone.class, QEmergencyPhone.class, PathInits.DIRECT2);

    public final QHealth health;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Medication, QMedication> medications = this.<Medication, QMedication>createList("medications", Medication.class, QMedication.class, PathInits.DIRECT2);

    public final StringPath memberEmail = createString("memberEmail");

    public final StringPath memberName = createString("memberName");

    public final StringPath memberPassword = createString("memberPassword");

    public final StringPath memberPhone = createString("memberPhone");

    public final ListPath<MemberSocial, QMemberSocial> memberSocials = this.<MemberSocial, QMemberSocial>createList("memberSocials", MemberSocial.class, QMemberSocial.class, PathInits.DIRECT2);

    public final ListPath<MemberStaff, QMemberStaff> memberStaffs = this.<MemberStaff, QMemberStaff>createList("memberStaffs", MemberStaff.class, QMemberStaff.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.health = inits.isInitialized("health") ? new QHealth(forProperty("health"), inits.get("health")) : null;
    }

}

