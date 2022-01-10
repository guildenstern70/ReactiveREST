/*
 * Project ReactREST
 * Copyright (c) Alessio Saltarin 2022
 * This software is licensed under MIT License (see LICENSE)
 */

package net.littlelite.ReactRest.controller.web;

import net.littlelite.ReactRest.ReactRestApplication;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home page controller
 */
@Controller
public class WebController
{
    private final Logger logger = LoggerFactory.getLogger(WebController.class);

    private static final String VERSION = ReactRestApplication.VERSION;

    /**
     * the REST request for / resource.
     *
     * @param model the HTTP request attributes. it will updated
     *              with application's version.
     * @return the home page
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/index")
    public String index(@NotNull Model model)
    {
        logger.info("/index page");

        model.addAttribute("version", WebController.VERSION);
        return "index";
    }

    /**
     * the REST request for / resource.
     *
     * @return redirect to the index page
     **/
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/")
    public String home()
    {
        return "redirect:index";
    }


}