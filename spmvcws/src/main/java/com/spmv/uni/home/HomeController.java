package com.spmv.uni.home;

import io.sphere.sdk.client.BlockingSphereClient;
import io.sphere.sdk.products.ProductProjection;
import io.sphere.sdk.products.queries.ProductProjectionQuery;
import io.sphere.sdk.queries.PagedQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;
import java.util.Locale;

@Controller
@RestController
public class HomeController {

	@Autowired
	private BlockingSphereClient sphereClient;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public CompletionStage<String> index(final Model model) {
        return sphereClient.execute(ProductProjectionQuery.ofCurrent())
                .thenApply((PagedQueryResult<ProductProjection> queryResult) -> {
                    model.addAttribute("allProducts", queryResult.getResults());
                    model.addAttribute("locale", Locale.ENGLISH);
                    return "home/index";
                });
	}
}