package com.alibou.security.delivery;

import org.springframework.stereotype.Service;
import com.alibou.security.command.Command;
import com.alibou.security.command.CommandRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final CommandRepository commandRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, CommandRepository commandRepository) {
        this.deliveryRepository = deliveryRepository;
        this.commandRepository = commandRepository;
    }

    public void addDeliveryLocation(Long commandId, double latitude, double longitude) {
        Command command = commandRepository.findById(commandId)
                .orElseThrow(() -> new IllegalArgumentException("Command not found"));

        Delivery delivery = new Delivery();
        delivery.setCommand(command);
        delivery.setLatitude(latitude);
        delivery.setLongitude(longitude);

        deliveryRepository.save(delivery);
    }
}