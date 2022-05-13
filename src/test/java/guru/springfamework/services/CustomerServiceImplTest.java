package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.CustomerMapper;
import guru.springfamework.api.v1.model.CustomerDTO;
import guru.springfamework.domain.Customer;
import guru.springfamework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    void getAllCustomers() {
        // given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Cook");
        customer1.setLastname("Meal");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstname("Walter");
        customer2.setLastname("Pastor");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        // when
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();

        // then
        assertEquals(2, allCustomers.size());
    }

    @Test
    void getCustomerById() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstname("Michale");
        customer1.setLastname("Weston");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        //then
        assertEquals("Michale", customerDTO.getFirstname());
    }

    @Test
    public void createNewCustomer() throws Exception {
        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstname(customerDTO.getFirstname());
        savedCustomer.setLastname(customerDTO.getLastname());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDto = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstname(), savedDto.getFirstname());
        assertEquals("/api/v1/customer/1", savedDto.getCustomerUrl());
    }


    @Test
    void saveCustomerByDTO() {
//        // given
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setFirstname("Jim");
//
//        Customer savedCustomer = new Customer();
//        savedCustomer.setFirstname(customerDTO.getFirstname());
//        savedCustomer.setLastname(customerDTO.getLastname());
//        savedCustomer.setId(1L);
//
//        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);
//        // when
    }

    @Test
    void patchCustomer() {
    }

    @Test
    void deleteCustomerById() {
        Long id = 1L;
        customerRepository.deleteById(id);
        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}