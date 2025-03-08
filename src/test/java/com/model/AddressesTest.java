package com.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AddressesTest {

    @Test
    public void testGettersAndSetters() {
        Addresses address = new Addresses();
        address.setAddressId(1L);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setState("Anystate");
        address.setZipCode("12345");

        assertEquals(1L, address.getAddressId());
        assertEquals("123 Main St", address.getStreet());
        assertEquals("Anytown", address.getCity());
        assertEquals("Anystate", address.getState());
        assertEquals("12345", address.getZipCode());
    }

    @Test
    public void testToString() {
        Addresses address = new Addresses();
        address.setAddressId(1L);
        address.setStreet("123 Main St");
        address.setCity("Anytown");
        address.setState("Anystate");
        address.setZipCode("12345");

        String expected = "Addresses [addressId=1, street=123 Main St, city=Anytown, state=Anystate, zipCode=12345]";
        assertEquals(expected, address.toString());
    }
}
