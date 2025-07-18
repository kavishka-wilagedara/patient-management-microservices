package com.pm.billingservice.grpc;

import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingAccount(billing.BillingRequest billingRequest,
                                     StreamObserver<BillingResponse> responseObserver) {
        log.info("CreateBillingAccount request received {}", billingRequest.toString());

//        Business logic (ex: database saving, perform calculate)

        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("123456")
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response); // Send response from grpc service
        responseObserver.onCompleted();
    }
}
