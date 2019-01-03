package com.bh.sample;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StuffApiDelegateImpl implements StuffApiDelegate {
    @Override
    public ResponseEntity<com.bh.sample.WorkedOperationStatus> doStuff(final com.bh.sample.Workload workload) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(new com.bh.sample.WorkedOperationStatus()
                        .status(com.bh.sample.WorkedOperationStatus.StatusEnum.SUCCESS)
                        .message("It worked")
                        .comment(workload.getComment())
                );
    }
}
