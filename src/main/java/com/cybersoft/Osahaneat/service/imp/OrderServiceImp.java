package com.cybersoft.Osahaneat.service.imp;

import com.cybersoft.Osahaneat.payload.request.OrderRequest;

public interface OrderServiceImp {
    boolean insertOrder(OrderRequest orderRequest);
}
