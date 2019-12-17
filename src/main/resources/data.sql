-- Системийн анхны чиглүүлэлтыг оруулж байна.
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5007, null, 'usergroup-add', null, 'Багын удирдлага', '/user');
INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5002, null, 'inbox', null, 'Шуудан', '/inbox');
-- INSERT INTO ims.route (id, breadcrumb_parent_id, icon, menu_parent_id, name, route) VALUES (5003, null, 'snippets', null, 'Тайлан', '/report');
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
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1003, null, 'http://localhost:4000/api/v1/download/34e51f11-bcb0-48bb-b1d5-3b2fa7764ef6', 'test1@gmail.com', true, 'test', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'test', 'test@123', '90909090', null, 4);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1000, null, 'http://localhost:4000/api/v1/download/5431dc9f-b468-45f9-b863-3d1cb45e7a10', 'daornit2@gmail.com', true, 'Bat-Orgil', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'Davaajantsan', 'test@123', '89898989', null, 4);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1002, null, 'http://localhost:4000/api/v1/download/72215985-0446-47d1-bc3f-62c2454e860c', 'test@gmail.com', true, 'Admin', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'System', 'test@123', '09090909', null, 4);
INSERT INTO ims."user" (id, address, avatar, email, enabled, first_name, group_code, last_name, password, phone, business_title, role) VALUES (1001, null, 'http://localhost:4000/api/v1/download/a20d6860-d1b9-47bc-a544-9391b429bf2b', 'john@gmail.com', true, 'Alex', '7fce98a2-464b-4a52-ba88-0d49d60c4c73', 'John', 'test@123', '80808080', null, 4);
-- Хэрэглэгчийн баталгаажуулах кодыг утгыг оруулч байна
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1000, true, 'c260f408-5f85-4509-8421-6da6cab0ee01', '2019-11-19 19:22:13.263000', 1000);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1001, false, 'c727b774-727a-421b-bdb7-dcd2e974166d', '2019-11-19 19:23:24.114000', 1001);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1002, true, '776af1d9-4286-46c1-a8e4-a02a13df49ca', '2019-11-19 19:24:02.875000', 1002);
INSERT INTO ims.confirmation_token (token_id, active, confirmation_token, created_date, id) VALUES (1003, true, '9fd76b0f-5cd8-46c9-ad06-75b9d4759511', '2019-11-19 19:24:27.883000', 1003);
-- Ажлын орчин үүсгэх
INSERT INTO ims.work_space (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, is_deleted, name, owner_id, team_code) VALUES (12, 1, null, '2019-11-27 14:08:27.877000', null, null, 1, 'c136e6e2-2755-4bbe-a39d-7044d0569415', true, 'Маркетинг', 1000, '3685c09d-8263-4504-b2a5-339544e4ae49');
INSERT INTO ims.work_space (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, is_deleted, name, owner_id, team_code) VALUES (11, 1, null, '2019-11-20 10:51:23.372000', null, null, 1, '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3', true, 'Хөгжүүлэлт орчин', 1000, 'b5537d00-4a34-4e99-b225-f3a466c76e6f');
INSERT INTO ims.work_space (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, is_deleted, name, owner_id, team_code) VALUES (13, 1, null, '2019-11-27 16:09:51.153000', null, null, 1, '13233fa4-56e4-4403-b49b-5b9a8da1c41b', false, 'Баклаврын судалгаа', 1000, 'e60c87d1-5e8d-4dfa-a192-7d7357d84bab');
-- Төсөл үүсгэх
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1000, 1, null, '2019-11-23 13:10:55.974000', null, null, 1, 'ab6b151c-3865-4a4e-ac7d-983225391bcf', '', 'list', '2019-11-22', false, 'Рболөр', null, '2019-10-29', '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3', 1000);
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1002, 1, null, '2019-11-27 13:57:34.436000', null, null, 1, '83323763-38b6-44f5-a937-30a763a8c41b', '<h1>Wrike системийн судалгаа</h1>

<p>өбрыйлдөобрйы дөрбдолый рдлөорый лдөобылой өрблдоыйрө лобрйы</p>
', 'list', '2019-11-30', false, 'Ижил төсөөтэй программ судалгаа', null, '2019-11-21', '6345ebaa-4a18-49a8-8ebb-35692d2ce4a3', 1000);
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1004, 1, null, '2019-11-27 16:13:09.967000', null, null, 1, 'fa51b8e7-2a19-4274-ab89-8f46fcefa59f', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', 'list', '2019-10-26', false, 'Системийн шинжилгээ ба зохиомж', null, '2019-10-05', '13233fa4-56e4-4403-b49b-5b9a8da1c41b', 1000);
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1005, 1, null, '2019-11-27 16:13:43.455000', null, null, 1, 'bbaf53f4-3b3d-4708-a2a9-622af32c1f67', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', 'list', '2019-12-14', false, 'Хэрэгжүүлэлт', null, '2019-10-26', '13233fa4-56e4-4403-b49b-5b9a8da1c41b', 1000);
INSERT INTO ims.work_package (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, default_view, end_date, is_deleted, name, process_status, start_date, work_space_code, owner_id) VALUES (1003, 1, null, '2019-11-27 16:11:42.764000', null, null, 1, '59a3698f-5180-4673-8b79-78795f37c202', '<blockquote>
<h3>Creativity системийн хийхийн тулд доорх судалгаануудыг хийх</h3>

<ol>
	<li>Ижил төстэй программын судалгаа</li>
	<li>Spring framework-ийн судалга</li>
</ol>
</blockquote>
', 'grantChart', '2019-10-05', false, 'Технологийн судалгаа', null, '2019-09-15', '13233fa4-56e4-4403-b49b-5b9a8da1c41b', 1000);
-- Баг үүсгэх
INSERT INTO ims.team (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, work_space_code) VALUES (1, 1, null, '2019-11-20 10:51:23.380000', null, null, 1, 'e60c87d1-5e8d-4dfa-a192-7d7357d84bab', '13233fa4-56e4-4403-b49b-5b9a8da1c41b');
-- Багын шигүүд нэмэх
INSERT INTO public.team_member (team_id, ims_user_id) VALUES (1, 1000);
INSERT INTO public.team_member (team_id, ims_user_id) VALUES (1, 1002);
-- Даалгавар үүсгэх
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (5, 1, null, '2019-11-27 13:59:42.739000', null, null, 1, 'b387a0df-0e11-42c9-b052-3a63df007acf', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-11-29 21:59:42.000000', true, 'Open project системийн судалгаа', null, 0, '2019-11-27 21:59:42.000000', 'complete', '83323763-38b6-44f5-a937-30a763a8c41b', 1000, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (3, 1, null, '2019-11-27 12:24:16.480000', null, null, 1, '2469eb2e-11ac-49af-93b1-a52f4e43b12b', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-11-27 20:24:16.000000', true, 'Шинэ даалгавар', null, 0, '2019-11-27 20:24:16.000000', 'new', 'ab6b151c-3865-4a4e-ac7d-983225391bcf', null, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (4, 1, null, '2019-11-27 12:24:19.371000', null, null, 1, '776a56b8-2241-4adc-8ed5-7c4d25182520', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-11-27 20:24:19.000000', true, 'Шинэ даалгавар', null, 0, '2019-11-27 20:24:19.000000', 'new', 'ab6b151c-3865-4a4e-ac7d-983225391bcf', null, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (6, 1, null, '2019-11-27 14:00:30.164000', null, null, 1, '252918f4-ce85-4eb9-9091-17aab55ff56c', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-12-01 21:59:42.000000', true, 'Шинэ даалгавар', 5, 0, '2019-11-30 21:59:42.000000', 'new', '83323763-38b6-44f5-a937-30a763a8c41b', null, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (7, 1, null, '2019-11-27 14:00:35.892000', null, null, 1, 'db257e33-1686-422a-925f-c7595c65c767', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-12-10 21:59:42.000000', true, 'Шинэ даалгавар', 6, 0, '2019-12-08 21:59:42.000000', 'new', '83323763-38b6-44f5-a937-30a763a8c41b', 1003, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (8, 1, null, '2019-11-27 16:21:30.693000', null, null, 1, '3fa1ad08-e0e8-4d64-b3dd-bb8d8f5e58db', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-09-29 00:22:01.000000', true, 'Ижил төстэй программын судалгаа', null, 0, '2019-09-23 00:22:01.000000', 'new', '59a3698f-5180-4673-8b79-78795f37c202', null, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (2, 1, null, '2019-11-27 12:24:14.072000', null, null, 1, 'cce887e4-82f8-4bd4-97b6-312ccb7d2cfa', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-12-28 20:24:10.000000', true, 'Шинэ даалгавар', null, 0, '2019-12-26 20:24:10.000000', 'new', 'ab6b151c-3865-4a4e-ac7d-983225391bcf', 1000, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (11, 1, null, '2019-11-27 16:22:01.804000', null, null, 1, 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-09-22 00:22:01.000000', false, 'Серверийн судалгаа', null, 0, '2019-09-16 00:22:01.000000', 'new', '59a3698f-5180-4673-8b79-78795f37c202', 1000, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (9, 1, null, '2019-11-27 16:21:40.637000', null, null, 1, '11a92eaa-65cb-40ea-b5e4-0f0e14899295', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-10-06 00:22:01.000000', false, 'Spring framework-ийн судалгаа', null, 0, '2019-09-30 00:22:01.000000', 'new', '59a3698f-5180-4673-8b79-78795f37c202', 1000, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (1, 1, null, '2019-11-27 12:24:10.476000', null, null, 1, 'a113bd3f-5211-4de9-bed9-0c854944c6a6', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-12-24 20:24:10.000000', true, 'Шинэ даалгавар', null, 0, '2019-12-22 20:24:10.000000', 'new', 'ab6b151c-3865-4a4e-ac7d-983225391bcf', 1000, 1000);
INSERT INTO ims.task (id, active_flag, created_by, created_date, updated_by, updated_date, version, code, content, end_date, is_deleted, name, parent_id, process, start_date, task_status, work_package_code, assign_id, owner_id) VALUES (10, 1, null, '2019-11-27 16:21:52.914000', null, null, 1, 'e3e29abb-5bd7-4324-8f40-472f2aeb212f', 'Энэхүү хэсэгт төслийн тайлбарыг бичнэ үү', '2019-10-05 00:22:01.000000', false, 'React JS технологийн судалгаа', null, 0, '2019-10-01 00:22:01.000000', 'complete', '59a3698f-5180-4673-8b79-78795f37c202', null, 1000);

-- file сан
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('3b19038b-b387-4d8b-9840-4d77e83be0f5', 'testeststestestest', 'text/x-c', '5_heap.cpp', '/Users/batorgild/uploads/016e1165-4479-40f0-8446-aeea895a6d45');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('dd566890-98ff-4d86-bd17-131673d342e9', 'testeststestestest', 'text/x-c', '5_heap.cpp', '/Users/batorgild/uploads/99f576d5-f093-4a0f-ad35-a04f6a27674c');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('be499d45-7063-41bc-bced-bec37b26b109', 'testeststestestest', 'text/x-c', '5_heap.cpp', '/Users/batorgild/uploads/0526cb27-30c0-4f8d-9dab-e0d4c5b73e6a');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('4a8273ee-f1af-4c1b-9b02-f666aa91abfd', 'testeststestestest', 'text/x-c', '5_heap.cpp', '/Users/batorgild/uploads/266a3054-edd5-4d17-a633-03f349f39e2a');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('687691f9-eae9-4295-ad0d-b7f9e4f4568a', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'Бакалаврын судалгааны ажлын  үечилсэн төлөвлөгөөний загвар 2018-2019.docx', '/Users/batorgild/uploads/5c6f10af-0be0-40e6-8c09-56396ed940ff');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('137be2f7-aeb0-4bd2-9bf1-a1426f02f936', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/pdf', 'Эцсийн тайлан.pdf', '/Users/batorgild/uploads/ac5c282c-85cc-4656-84e6-e5609ae8f589');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('87e54242-d6d2-4d73-b89d-14ed6ad51607', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/pdf', 'Quality Attributes for Technical Testing.pdf', '/Users/batorgild/uploads/19cbac01-c20b-4d47-9e55-004b9d34ed99');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('c96f87b2-d89c-4886-a208-eb6edbb165ff', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'Бакалаврын судалгааны ажлын  үечилсэн төлөвлөгөөний загвар 2018-2019.docx', '/Users/batorgild/uploads/d35109d5-f0cb-4432-8fd8-538458254abd');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('3ca54614-9293-49a5-9b8b-e94e84ed0fc3', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'Бакалаврын судалгааны ажлын  үечилсэн төлөвлөгөөний загвар 2018-2019.docx', '/Users/batorgild/uploads/c7623fbf-93c1-4423-a142-864f8f0d1be4');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('f416db03-53cb-4eaf-95f9-b4978b3fa7f3', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/msword', 'Task Management System - SRS.doc', '/Users/batorgild/uploads/81265486-327d-4147-996e-6a502a40d7e2');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('b684ca0d-39e8-46f6-b1f1-6f8563deaaa0', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/msword', 'Task Management System - SRS.doc', '/Users/batorgild/uploads/97132603-4677-4396-a031-b4c8d0217569');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('7df29f06-11a2-40c0-bbb4-1bbe5e9d930f', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/msword', 'Task Management System - SRS.doc', '/Users/batorgild/uploads/b22eb222-963c-4032-be6f-a4b1e1bbaa75');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('c51b2242-76c9-45d7-a989-20c84c5254aa', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'chanar test.docx', '/Users/batorgild/uploads/2d8b337d-9ce2-4249-80a9-1ed18c533e42');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('8770f6c0-47bb-493f-b454-884cbf1036a3', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'application/octet-stream', 'Ant.Design.Pro.sketch', '/Users/batorgild/uploads/0e271475-9d60-4090-b93d-dd6e02011869');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('72ecd88c-fdaf-47b6-a94b-cb810d290688', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'image/jpeg', '1040734.jpg', '/Users/batorgild/uploads/d8f36cd1-dcf4-48f9-b411-deb65f06ba8c');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('1070b05c-5dea-4885-8c67-11745ff301ec', 'f6eab05b-0019-4008-9a4d-2d57f36bf9e2', 'image/jpeg', '1040734.jpg', '/Users/batorgild/uploads/e6579ac2-f908-4550-8b63-6c56dfb79b15');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('399048e0-981c-4ecc-938b-453fcb35f575', 'user-avatar', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/93e1b95f-440d-4b7f-ab3e-6976853320c2');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('61e44e32-a408-4cf4-b0c2-d916c59ac391', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/4a6c4e27-26bc-411d-8b7f-ad2ab6bb38ce');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('ce80efec-4d6e-4e9f-a41d-03a5def5c4b3', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/efcf43ca-7581-44f2-ba61-ef52c68758fc');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('52ca61be-a29e-4f16-b619-8a5b589ac80b', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/a4f6cd19-3d8c-4d15-9875-919428da1617');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('dc355d6c-1a29-4d27-8da0-0be1e931d0b1', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/fddfc907-dafc-46c2-8e43-981ca6ac03f7');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('cee1a412-7a39-4485-b3e5-1efaaa14586c', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/2cf7a6c8-b765-44eb-9e16-366ae2f7c1a0');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('d18235ad-c74d-436a-91ad-055600769e18', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/8419d023-ded8-46c0-88af-ab61fc3d8543');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('1cb1c79a-3e73-4a25-a06c-dfe5910187dd', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/efa06dff-a3a5-4421-b1b9-cbd6fec2db2b');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('0e12bf3d-e3e6-417e-ae18-bbbecbd339b8', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/7abb7678-fe96-4bd5-b492-04bb6f18f669');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('e30d44e5-82aa-40f0-b0dd-e1262a8ba927', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/dfca9fc5-4b19-4525-b56e-782324be723c');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('c0714d3b-b2c1-4055-9bc6-415a969874e1', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/bb204197-d388-4369-8740-99f8802876ea');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('30ed74fc-00b3-43ce-b4dd-481694c683ce', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/bab99d92-c2f1-49bc-98fa-d7ef3498e12c');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('34e51f11-bcb0-48bb-b1d5-3b2fa7764ef6', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/f31d012d-4003-467d-a10c-0fa390df3f74');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('641cb6cb-fee7-4c7d-9a09-6a2a1f801917', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/16122c67-aec4-49b8-a3a8-3594c160a5ad');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('b5db4bcc-41a4-4b9e-aca7-06545802e809', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/00316721-e365-4204-9500-44e9d82e0e37');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('5431dc9f-b468-45f9-b863-3d1cb45e7a10', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/eb56304d-b4a4-4a92-a19a-45d667a18230');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('72215985-0446-47d1-bc3f-62c2454e860c', 'user', 'image/png', 'c2f970-07a0c3-f0f7f4-2f3061-ee6352.png', '/Users/batorgild/uploads/afb36b67-b95b-40bd-b906-b38075c9f572');
INSERT INTO ims.file_store (id, code, content_type, name, path) VALUES ('a20d6860-d1b9-47bc-a544-9391b429bf2b', 'user', 'image/png', 'avatar.png', '/Users/batorgild/uploads/7ca2a1b5-b35c-45a3-9f41-fa7a1b994829');
