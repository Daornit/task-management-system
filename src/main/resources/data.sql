-- Системийн анхны чиглүүлэлтыг оруулж байна.
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5007, null, 'usergroup-add', null, 'Багын удирдлага', '/user');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5002, null, 'inbox', null, 'Шуудан', '/inbox');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5003, null, 'snippets', null, 'Тайлан', '/report');
-- INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5006, null, 'delete', null, 'Сэргээх', '/recovery');recovery
-- INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5011, 1, 'font-size', 1, 'Тэмдэглэл', '/note');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5012, 1, 'check-square', 1, 'Хийх даалгаврууд', '/to-do');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (1, null, 'user', null, 'Хувын ажлын орчин', '/personal-space');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5013, 1, 'solution', 1, 'Үүсгэсэн даалгаврууд', '/to-assign');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5008, null, 'unordered-list', null, 'Ажлын орчин удирдлага', '/work-space');
-- Хэрэгэлэгчийн үүргийг оруулч байна
INSERT INTO ims.role (id, is_active, role) VALUES (2, true, 'admin');
INSERT INTO ims.role (id, is_active, role) VALUES (3, true, 'leader');
INSERT INTO ims.role (id, is_active, role) VALUES (1, true, 'manager');
INSERT INTO ims.role (id, is_active, role) VALUES (4, true, 'member');
-- Системийн эрхийг оруулж байна
INSERT INTO ims.ims_permission (id, description, permission, role_id) VALUES (1, 'Ажлын орчин үүгэх', 'ROLE_CREATE_WORK_SPACE', 1);
INSERT INTO ims.ims_permission (id, description, permission, role_id) VALUES (2, 'Ажлын багц үүгэх', 'ROLE_CREATE_WORK_PACKAGE', 1);
INSERT INTO ims.ims_permission (id, description, permission, role_id) VALUES (3, 'Даалгавар үүгэх', 'ROLE_CREATE_TASK', 1);
-- Хэрэгэлгчийн мэдээллийг оруулч байна
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1000, null, 'http://localhost:4000/api/v1/downloadFile/avatar.png', 'daornit2@gmail.com', true, 'Bat-Orgil', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'Davaajantsan', 'test@123', '89898989', null, 1);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1001, null, 'http://localhost:4000/api/v1/downloadFile/avatar.png', 'john@gmail.com', true, 'Alex', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'John', 'test@123', '80808080', null, 4);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1002, null, 'http://localhost:4000/api/v1/downloadFile/avatar.png', 'test@gmail.com', true, 'Admin', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'System', 'test@123', '09090909', null, 3);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1003, null, 'http://localhost:4000/api/v1/downloadFile/avatar.png', 'test1@gmail.com', true, 'test', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'test', 'test@123', '90909090', null, 4);
-- Хэрэглэгчийн баталгаажуулах кодыг утгыг оруулч байна
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1000, true, 'c260f408-5f85-4509-8421-6da6cab0ee01', '2019-11-19 19:22:13.263000', 1000);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1001, true, 'c727b774-727a-421b-bdb7-dcd2e974166d', '2019-11-19 19:23:24.114000', 1001);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1002, true, '776af1d9-4286-46c1-a8e4-a02a13df49ca', '2019-11-19 19:24:02.875000', 1002);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1003, true, '9fd76b0f-5cd8-46c9-ad06-75b9d4759511', '2019-11-19 19:24:27.883000', 1003);
-- Ажлын орчин үүсгэх
INSERT INTO ims.work_space (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, is_deleted, name, owner_id, team_code) VALUES (1, 1, null, '2019-11-20 10:51:23.372000', null, null, 1, '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3', false, 'Хөгжүүлэлт орчин', 1000, 'b5537d00-4a34-4e99-b225-f3a466c76e6f');
-- Төсөл үүсгэх
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1, 1, null, '2019-11-23 13:10:55.974000', null, null, 1, 'ab6b151c-3865-4a4e-ac7d-983225391bcf', '', 'list', '2019-11-22', false, 'Рболөр', null, '2019-10-29', '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3', 1000);
-- Баг үүсгэх
INSERT INTO ims.team (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, work_space_code) VALUES (1, 1, null, '2019-11-20 10:51:23.380000', null, null, 1, 'b5537d00-4a34-4e99-b225-f3a466c76e6f', '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3');
-- Багын шигүүд нэмэх
INSERT INTO public.team_member (team_id, ims_user_id) VALUES (1, 1000);
INSERT INTO public.team_member (team_id, ims_user_id) VALUES (1, 1002);

