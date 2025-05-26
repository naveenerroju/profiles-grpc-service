package org.github.naveenerroju.profile_service.service;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import org.github.naveenerroju.profile_service.Profile;
import org.github.naveenerroju.profile_service.ProfileId;
import org.github.naveenerroju.profile_service.ProfileResponse;
import org.github.naveenerroju.profile_service.ProfileServiceGrpc;
import org.github.naveenerroju.profile_service.entity.ProfileEntity;
import org.github.naveenerroju.profile_service.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.grpc.server.service.GrpcService;

import java.util.Optional;
import java.util.UUID;

@GrpcService
public class ProfileService extends ProfileServiceGrpc.ProfileServiceImplBase {

    @Autowired
    private ProfileRepository repository;

    @Override
    public void addProfile(Profile request, StreamObserver<ProfileResponse> responseObserver) {
        ProfileEntity entity = new ProfileEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        ProfileEntity dbResponse = repository.save(entity);

        ProfileResponse response = ProfileResponse.newBuilder().setMessage("Profile added: "+dbResponse.getId()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateProfile(Profile request, StreamObserver<ProfileResponse> responseObserver) {
        Optional<ProfileEntity> optional = repository.findById(request.getId());
        if (!optional.isPresent()) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Profile not found").asRuntimeException());
            return;
        }
        ProfileEntity entity = optional.get();
        entity.setName(request.getName());
        entity.setEmail(request.getEmail());
        entity.setPhone(request.getPhone());
        repository.save(entity);

        ProfileResponse response = ProfileResponse.newBuilder().setMessage("Profile updated").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getProfile(ProfileId request, StreamObserver<Profile> responseObserver) {
        Optional<ProfileEntity> optional = repository.findById(request.getId());
        if (!optional.isPresent()) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Profile not found").asRuntimeException());
            return;
        }
        ProfileEntity entity = optional.get();
        Profile profile = Profile.newBuilder()
                .setId(entity.getId())
                .setName(entity.getName())
                .setEmail(entity.getEmail())
                .setPhone(entity.getPhone())
                .build();
        responseObserver.onNext(profile);
        responseObserver.onCompleted();
    }

    @Override
    public void deleteProfile(ProfileId request, StreamObserver<ProfileResponse> responseObserver) {
        if (!repository.existsById(request.getId())) {
            responseObserver.onError(Status.NOT_FOUND.withDescription("Profile not found").asRuntimeException());
            return;
        }
        repository.deleteById(request.getId());
        ProfileResponse response = ProfileResponse.newBuilder().setMessage("Profile deleted").build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
