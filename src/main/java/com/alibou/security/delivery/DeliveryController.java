package com.alibou.security.delivery;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
@CrossOrigin(origins = "http://localhost:4200")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final ObjectMapper objectMapper;

    public DeliveryController(DeliveryService deliveryService, ObjectMapper objectMapper) {
        this.deliveryService = deliveryService;
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/{commandId}/location", produces = "application/json")
    public ResponseEntity<DeliveryResponse> addDeliveryLocation(@PathVariable Long commandId, @RequestBody DeliveryRequest location) {
        try {
            deliveryService.addDeliveryLocation(commandId, location.getLatitude(), location.getLongitude());
            DeliveryResponse response = new DeliveryResponse("Delivery location added successfully!");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            DeliveryResponse response = new DeliveryResponse("Failed to add delivery location");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DeliveryResponse> handleException(Exception ex) {
        DeliveryResponse response = new DeliveryResponse("An error occurred");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}