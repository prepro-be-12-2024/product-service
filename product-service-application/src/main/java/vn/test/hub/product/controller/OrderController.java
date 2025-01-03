package vn.test.hub.product.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.test.hub.common.info.PaginationInfo;
import vn.test.hub.common.response.BaseResponse;
import vn.test.hub.common.utils.ResponseUtils;
import vn.test.hub.product.domain.Order;
import vn.test.hub.product.domain.OrderItem;
import vn.test.hub.product.dto.request.CreateOrderRequest;
import vn.test.hub.product.mapper.OrderMapper;
import vn.test.hub.product.service.IOrderItemService;
import vn.test.hub.product.service.IOrderService;
import vn.test.hub.product.utils.SecurityUtils;

import java.util.List;

@RequestMapping("/api/v1/")
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    private final IOrderItemService orderItemService;
    private final OrderMapper orderMapper;

    @GetMapping("/orders")
    public ResponseEntity<BaseResponse<List<Order>, PaginationInfo>> findAll(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageParam,
            @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limitParam,
            @RequestParam(value = "status", required = false) String statusParam,
            @RequestParam(value = "userID", required = false) String userIDParam,
            @RequestParam(value = "sortCreatedAt", required = false) String sortCreatedAtParam,
            @RequestParam(value = "sortTotalPrice", required = false) String sortTotalPriceParam) {

        return ResponseUtils.success("success",
                orderService.findAll(pageParam, limitParam, statusParam, userIDParam, sortCreatedAtParam, sortTotalPriceParam),
                PaginationInfo.builder()
                        .currentPage(pageParam)
                        .limit(limitParam)
                        .build());
    }

    @GetMapping("/users/orders")
    public ResponseEntity<BaseResponse<List<Order>, PaginationInfo>> findAllByUserID(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageParam,
            @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limitParam,
            @RequestParam(value = "status", required = false) String statusParam,
            @RequestParam(value = "sortCreatedAt", required = false) String sortCreatedAtParam,
            @RequestParam(value = "sortTotalPrice", required = false) String sortTotalPriceParam) {

        return ResponseUtils.success("success",
                orderService.findAll(pageParam, limitParam, statusParam, SecurityUtils.getUserID(), sortCreatedAtParam, sortTotalPriceParam),
                PaginationInfo.builder()
                        .currentPage(pageParam)
                        .limit(limitParam)
                        .build());
    }

    @PostMapping("/orders")
    public ResponseEntity<BaseResponse<Order, Void>> createOrder(@RequestBody CreateOrderRequest orderRequest) {
        String userID = SecurityUtils.getUserID();

        Order order = orderMapper.toOrder(orderRequest);
        order.setUserID(userID);
        order = orderService.create(order);

        return ResponseUtils.created(order);
    }

    @PostMapping("/users/orders/{orderID}/complete")
    public ResponseEntity<BaseResponse<Order, Void>> updateOrder(@PathVariable("orderID") String id) {
        Order order = orderService.completeOrder(id, SecurityUtils.getUserID());

        return ResponseUtils.created(order);
    }

    @GetMapping("/users/orders/{orderID}")
    public ResponseEntity<BaseResponse<List<OrderItem>, PaginationInfo>> findAllOrderItemByOrderID(
            @RequestParam(value = "page", required = false, defaultValue = "1") int pageParam,
            @RequestParam(value = "limit", required = false, defaultValue = "2") Integer limitParam,
            @PathVariable("orderID") String orderID) {

        return ResponseUtils.success("success",
                orderItemService.findAllByOrderID(pageParam, limitParam, orderID),
                PaginationInfo.builder()
                        .currentPage(pageParam)
                        .limit(limitParam)
                        .build());
    }
}
