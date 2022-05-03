INSERT INTO tb_login (email, password) VALUES ('roberta@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');
INSERT INTO tb_login (email, password) VALUES ('matheus@gmail.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG');

INSERT INTO tb_role (authority) VALUES ('ROLE_LOGIN');
INSERT INTO tb_role (authority) VALUES ('ROLE_ADMIN');

INSERT INTO tb_login_role (login_id, role_id) VALUES (1, 1);
INSERT INTO tb_login_role (login_id, role_id) VALUES (2, 2);

INSERT INTO tb_user(s_nome, s_sobrenome, s_email, int_idade, boolean_ativo) VALUES ('Roberta', 'Siqueira', 'roberta@gmail.com', 38, true);
INSERT INTO tb_user(s_nome, s_sobrenome, s_email, int_idade, boolean_ativo) VALUES ('Matheus', 'Rocha', 'matheus@gmail.com', 32, true);

INSERT INTO tb_user_documents(s_rg, orgao_emissor, orgao_emissor_estado, s_cpf, s_sus, doc_user_id) VALUES ('10101010', 2, 11, '010.010.010-001', '1111111111', 1);
INSERT INTO tb_user_documents(s_rg, orgao_emissor, orgao_emissor_estado, s_cpf, s_sus, doc_user_id) VALUES ('20202020', 5, 25, '020.020.020-002', '2222222222', 2);