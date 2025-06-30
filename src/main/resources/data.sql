-- EXCHANGE_RATES
INSERT INTO EXCHANGE_RATES (`from`, `to`, rate, valid_at) VALUES ('USD', 'EUR', 0.850000, '2023-01-01 00:00:00');
INSERT INTO EXCHANGE_RATES (`from`, `to`, rate, valid_at) VALUES ('USD', 'GBP', 0.750000, '2023-01-01 00:00:00');
INSERT INTO EXCHANGE_RATES (`from`, `to`, rate, valid_at) VALUES ('EUR', 'USD', 1.176471, '2023-01-01 00:00:00');
INSERT INTO EXCHANGE_RATES (`from`, `to`, rate, valid_at) VALUES ('GBP', 'USD', 1.333333, '2023-01-01 00:00:00');
INSERT INTO EXCHANGE_RATES (`from`, `to`, rate, valid_at) VALUES ('EUR', 'GBP', 0.882353, '2023-01-01 00:00:00');

-- BOOKS
INSERT INTO BOOKS (id, slug, publication_date, category, last_updated, title, description, toc, amount, discounted_amount, tags, pages, eap, vcs, currency, tax_code, thumbnail)
VALUES (1, 'spring-boot-guide', '2023-01-15', 'Programming', '2023-06-20', 'Spring Boot Guide', 'Comprehensive guide to Spring Boot framework', 'Chapter 1: Introduction\nChapter 2: Getting Started\nChapter 3: Advanced Topics', 4999, 3999, 'spring,java,programming', 350, b'0', 'git', 'USD', 'ebook', 'https://example.com/thumbnails/spring-boot.jpg');

INSERT INTO BOOKS (id, slug, publication_date, category, last_updated, title, description, toc, amount, discounted_amount, tags, pages, eap, vcs, currency, tax_code, thumbnail)
VALUES (2, 'kotlin-for-java-developers', '2023-03-10', 'Programming', '2023-07-15', 'Kotlin for Java Developers', 'Learn Kotlin from a Java developer perspective', 'Chapter 1: Kotlin Basics\nChapter 2: Object-Oriented Programming\nChapter 3: Functional Programming', 3999, 2999, 'kotlin,java,programming', 280, b'0', 'git', 'USD', 'ebook', 'https://example.com/thumbnails/kotlin.jpg');

-- GUIDES
INSERT INTO GUIDES (id, slug, title, amount, discounted_amount, thumbnail, type, publication_date, last_updated, currency, tax_code)
VALUES (1, 'spring-security-guide', 'Spring Security Guide', 2999, 2499, 'https://example.com/thumbnails/spring-security.jpg', 'STANDARD', '2023-02-20', '2023-08-10', 'USD', 'ebook');

INSERT INTO GUIDES (id, slug, title, amount, discounted_amount, thumbnail, type, publication_date, last_updated, currency, tax_code)
VALUES (2, 'microservices-architecture', 'Microservices Architecture', 3499, 2999, 'https://example.com/thumbnails/microservices.jpg', 'PREMIUM', '2023-04-05', '2023-09-01', 'USD', 'ebook');

INSERT INTO GUIDES (id, slug, title, amount, discounted_amount, thumbnail, type, publication_date, last_updated, currency, tax_code)
VALUES (3, 'docker-for-developers', 'Docker for Developers', 1999, 1499, 'https://example.com/thumbnails/docker.jpg', 'STANDARD', '2023-05-15', '2023-08-20', 'USD', 'ebook');

-- PLUS_GUIDES
INSERT INTO PLUS_GUIDES (id, slug, ascii_doc, amount, discounted_amount, total_eap_slots, taken_eap_slots, type, screencasts_download_url, currency, tax_code)
VALUES (1,'spring-boot-masterclass', '= Spring Boot Masterclass\n\nA comprehensive guide to mastering Spring Boot.', 5999, 4999, 20, 15, 'EAP', 'https://example.com/downloads/spring-boot-masterclass', 'USD', 'eservice');

INSERT INTO PLUS_GUIDES (id, slug, ascii_doc, amount, discounted_amount, total_eap_slots, taken_eap_slots, type, screencasts_download_url, currency, tax_code)
VALUES (2, 'advanced-java-techniques', '= Advanced Java Techniques\n\nDeep dive into advanced Java programming.', 4999, 3999, 20, 10, 'EAP', 'https://example.com/downloads/advanced-java', 'USD', 'eservice');

INSERT INTO PLUS_GUIDES (id, slug, ascii_doc, amount, discounted_amount, total_eap_slots, taken_eap_slots, type, screencasts_download_url, currency, tax_code)
VALUES (3, 'cloud-native-development', '= Cloud Native Development\n\nBuild cloud-native applications with modern technologies.', 6999, 5999, 20, 5, 'EAP', 'https://example.com/downloads/cloud-native', 'USD', 'eservice');

-- PRODUCT_TEAM_SIZES
INSERT INTO PRODUCT_TEAM_SIZES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (1,'Individual', 'License for a single developer', 1, NULL, NULL);

INSERT INTO PRODUCT_TEAM_SIZES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (2, 'Team (5)', 'License for up to 5 developers', 1, NULL, NULL);

INSERT INTO PRODUCT_TEAM_SIZES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (3, 'Enterprise', 'License for unlimited developers', 1, NULL, NULL);

INSERT INTO PRODUCT_TEAM_SIZES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (4, 'Individual', 'License for a single developer', NULL, 1, NULL);

INSERT INTO PRODUCT_TEAM_SIZES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (5, 'Individual', 'License for a single developer', NULL, NULL, 1);

-- PRODUCT_PACKAGES
INSERT INTO PRODUCT_PACKAGES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (1, 'Basic', 'Basic package with essential content', 1, NULL, NULL);

INSERT INTO PRODUCT_PACKAGES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (2, 'Premium', 'Premium package with additional content', 1, NULL, NULL);

INSERT INTO PRODUCT_PACKAGES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (3, 'Basic', 'Basic package with essential content', NULL, 1, NULL);

INSERT INTO PRODUCT_PACKAGES (id, name, description, plus_guide_id, guide_id, book_id)
VALUES (4, 'Basic', 'Basic package with essential content', NULL, NULL, 1);

-- PRODUCT_PRICING
INSERT INTO PRODUCT_PRICING (plus_guide_id, guide_id, book_id, package_id, team_size_id, amount, title, subtitle, discounted_amount, currency, is_active)
VALUES (1, NULL, NULL, 1, 1, 5999, 'Spring Boot Masterclass - Basic - Individual', 'For single developers', 4999, 'USD', 1);

INSERT INTO PRODUCT_PRICING (plus_guide_id, guide_id, book_id, package_id, team_size_id, amount, title, subtitle, discounted_amount, currency, is_active)
VALUES (1, NULL, NULL, 1, 2, 19999, 'Spring Boot Masterclass - Basic - Team', 'For teams up to 5 developers', 17999, 'USD', 1);

INSERT INTO PRODUCT_PRICING (plus_guide_id, guide_id, book_id, package_id, team_size_id, amount, title, subtitle, discounted_amount, currency, is_active)
VALUES (NULL, 1, NULL, 3, 3, 2999, 'Spring Security Guide - Basic - Individual', 'For single developers', 2499, 'USD', 1);

INSERT INTO PRODUCT_PRICING (plus_guide_id, guide_id, book_id, package_id, team_size_id, amount, title, subtitle, discounted_amount, currency, is_active)
VALUES (NULL, NULL, 1, 4, 4, 4999, 'Spring Boot Guide - Basic - Individual', 'For single developers', 3999, 'USD', 1);

-- TAX_RATES
INSERT INTO TAX_RATES (country, vat_number, NAME, NOTES, RATE, valid_until, tax_code)
VALUES ('US', NULL, 'United States', 'No VAT', 0.0, NULL, 'ebook');

INSERT INTO TAX_RATES (country, vat_number, NAME, NOTES, RATE, valid_until, tax_code)
VALUES ('DE', NULL, 'Germany', 'Standard VAT rate', 19.0, NULL, 'ebook');

INSERT INTO TAX_RATES (country, vat_number, NAME, NOTES, RATE, valid_until, tax_code)
VALUES ('GB', NULL, 'United Kingdom', 'Standard VAT rate', 20.0, NULL, 'ebook');

INSERT INTO TAX_RATES (country, vat_number, NAME, NOTES, RATE, valid_until, tax_code)
VALUES ('US', NULL, 'United States', 'No VAT', 0.0, NULL, 'eservice');

INSERT INTO TAX_RATES (country, vat_number, NAME, NOTES, RATE, valid_until, tax_code)
VALUES ('DE', NULL, 'Germany', 'Standard VAT rate', 19.0, NULL, 'eservice');

-- REGIONAL_DISCOUNTS (without referencing COURSES)
INSERT INTO REGIONAL_DISCOUNTS (percent, countries, continents, product_id)
VALUES (30, 'IN,BR,RU', NULL, 'spring-boot-guide');

INSERT INTO REGIONAL_DISCOUNTS (percent, countries, continents, product_id)
VALUES (20, NULL, 'AF,SA', 'kotlin-for-java-developers');

INSERT INTO REGIONAL_DISCOUNTS (percent, countries, continents, product_id)
VALUES (15, 'UA,MD,GE', NULL, 'spring-security-guide');