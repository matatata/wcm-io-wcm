/*
 * #%L
 * wcm.io
 * %%
 * Copyright (C) 2014 wcm.io
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package io.wcm.wcm.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.day.cq.wcm.api.Page;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class ToStringStyleAemObjectsTest {

  private final AemContext context = new AemContext();

  @Test
  void testResource() {
    Resource resource = context.create().resource("/my/resource");
    ToStringTester tester = new ToStringTester(resource, null, null);
    assertEquals("ToStringStyleAemObjectsTest.ToStringTester[resource=/my/resource]", tester.toString());
  }

  @Test
  void testResources() {
    Resource resource1 = context.create().resource("/my/resource1");
    Resource resource2 = context.create().resource("/my/resource2");
    ToStringTester tester = new ToStringTester(null, new Resource[] {
        resource1, resource2
    }, null);
    assertEquals("ToStringStyleAemObjectsTest.ToStringTester[resources={/my/resource1,/my/resource2}]", tester.toString());
  }

  @Test
  void testPage() {
    Page page = context.create().page("/my/page");
    ToStringTester tester = new ToStringTester(null, null, page);
    assertEquals("ToStringStyleAemObjectsTest.ToStringTester[page=/my/page]", tester.toString());
  }


  @SuppressWarnings("unused")
  private static class ToStringTester {

    private final Resource resource;
    private final Resource[] resources;
    private final Page page;

    ToStringTester(Resource resource, Resource[] resources, Page page) {
      this.resource = resource;
      this.resources = resources;
      this.page = page;
    }

    @Override
    public String toString() {
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_OMIT_NULL_STYLE);
    }

  }

}
