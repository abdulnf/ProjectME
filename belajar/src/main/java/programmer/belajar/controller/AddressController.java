//package programmer.belajar.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//import programmer.belajar.model.AddressResponse;
//import programmer.belajar.model.CreateAddressRequest;
//import programmer.belajar.model.UpdateAddressRequest;
//import programmer.belajar.model.WebResponse;
//import programmer.belajar.service.AddressService;
//import programmer.belajar.user.User;
//
//import java.util.List;
//
//@RestController
//
//public class AddressController {
//    @Autowired
//    private AddressService addressService;
//
//    @PostMapping(
//            path = "/api/contacts/{contactId}/addresses",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes= MediaType.APPLICATION_JSON_VALUE)
//
//
//    public WebResponse<AddressResponse> create(User user,
//                                               @RequestBody CreateAddressRequest request,
//                                               @PathVariable("contactId") String contactId) {
//
//        request.setContactId(contactId);
//        AddressResponse addressResponse = addressService.create(user, request);
//        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
//
//
//    }
//
//    @GetMapping(
//            path = "/api/contacts/{contactId}/addresses/{addressId}",
//            produces = MediaType.APPLICATION_JSON_VALUE
//
//    )
//
//    public WebResponse<AddressResponse> get(User user,
//                                            @PathVariable("contactId") String contactId,
//                                            @PathVariable("addressId") String addressId){
//        AddressResponse addressResponse = addressService.get(user, contactId, addressId);
//
//        return WebResponse .< AddressResponse>builder().data(addressResponse).build();
//
//    }
//
//    @PutMapping(
//            path = "/api/contacts/{contactId}/addresses/{addressId}",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE)
//
//    public WebResponse<AddressResponse> update(User user,
//                                               @RequestBody UpdateAddressRequest request,
//                                               @PathVariable("contactId") String contactId,
//                                               @PathVariable("addressId") String addressId) {
//
//        request.setContactId(contactId);
//        request.setAddressId(addressId);
//
//        AddressResponse addressResponse = addressService.update(user, request);
//        return WebResponse.<AddressResponse>builder().data(addressResponse).build();
//    }
//
//    @DeleteMapping(
//            path = "/api/contacts/{contactId}/addresses/{addressId}",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public WebResponse<String> remove(User user,
//                                               @PathVariable("contactId") String contactId,
//                                               @PathVariable("addressId") String addressId) {
//        addressService.remove(user, contactId, addressId);
//        return WebResponse .<String>builder().data("OK").build();
//    }
//
//
//
//    @GetMapping(
//            path = "/api/contacts/{contactId}/addresses",
//            produces = MediaType.APPLICATION_JSON_VALUE)
//
//    public WebResponse<List<AddressResponse>> list(User user,
//                                                   @PathVariable("contactId") String contactId) {
//        List<AddressResponse> addressResponses = addressService.list(user, contactId);
//        return WebResponse.<List<AddressResponse>>builder().data(addressResponses).build();
//    }
//}


package programmer.belajar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import programmer.belajar.model.*;
import programmer.belajar.service.AddressService;
import programmer.belajar.user.User;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/contacts/{contactId}/addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(
            @AuthenticationPrincipal User user,
            @RequestBody CreateAddressRequest request,
            @PathVariable String contactId
    ) {
        request.setContactId(contactId);
        return WebResponse.<AddressResponse>builder()
                .data(addressService.create(user, request))
                .build();
    }

    @GetMapping(
            path = "/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(
            @AuthenticationPrincipal User user,
            @PathVariable String contactId,
            @PathVariable String addressId
    ) {
        return WebResponse.<AddressResponse>builder()
                .data(addressService.get(user, contactId, addressId))
                .build();
    }

    @PutMapping(
            path = "/{addressId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(
            @AuthenticationPrincipal User user,
            @RequestBody UpdateAddressRequest request,
            @PathVariable String contactId,
            @PathVariable String addressId
    ) {
        request.setContactId(contactId);
        request.setAddressId(addressId);

        return WebResponse.<AddressResponse>builder()
                .data(addressService.update(user, request))
                .build();
    }

    @DeleteMapping(
            path = "/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(
            @AuthenticationPrincipal User user,
            @PathVariable String contactId,
            @PathVariable String addressId
    ) {
        addressService.remove(user, contactId, addressId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> list(
            @AuthenticationPrincipal User user,
            @PathVariable String contactId
    ) {
        return WebResponse.<List<AddressResponse>>builder()
                .data(addressService.list(user, contactId))
                .build();
    }
}