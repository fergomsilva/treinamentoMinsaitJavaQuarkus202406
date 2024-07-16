ALTER SEQUENCE pessoa_seq INCREMENT BY 1;
ALTER SEQUENCE cliente_seq INCREMENT BY 1;
ALTER SEQUENCE loja_seq INCREMENT BY 1;
ALTER SEQUENCE itempedido_seq INCREMENT BY 1;
ALTER SEQUENCE pedido_seq INCREMENT BY 1;
INSERT INTO pessoa (id, nome, endereco, cidade, uf, tipoDocumento, documento) VALUES (nextval('pessoa_seq'), 'João Silva', 'Rua das Flores, 123', 'São Paulo', 'SP', 0, '12345678900');
INSERT INTO cliente (id, pessoa_id, email) VALUES (nextval('cliente_seq'), currval('pessoa_seq'), 'joao@email.com');
INSERT INTO pessoa (id, nome, endereco, cidade, uf, tipoDocumento, documento) VALUES (nextval('pessoa_seq'), 'Empresa XYZ', 'Av. Comercial, 456', 'Rio de Janeiro', 'RJ', 1, '12345678000190');
INSERT INTO cliente (id, pessoa_id, email) VALUES (nextval('cliente_seq'), currval('pessoa_seq'), 'contato@empresa.com');
INSERT INTO pessoa (id, nome, endereco, cidade, uf, tipoDocumento, documento) VALUES (nextval('pessoa_seq'), 'Maria Souza', 'Rua das Palmeiras, 789', 'Belo Horizonte', 'MG', 0, '98765432100');
INSERT INTO cliente (id, pessoa_id, email) VALUES (nextval('cliente_seq'), currval('pessoa_seq'), 'maria@email.com');
INSERT INTO pessoa (id, nome, endereco, cidade, uf, tipoDocumento, documento) VALUES (nextval('pessoa_seq'), 'Empresa ABC', 'Rua Industrial, 567', 'Curitiba', 'PR', 1, '98765432000123');
INSERT INTO cliente (id, pessoa_id, email) VALUES (nextval('cliente_seq'), currval('pessoa_seq'), 'contato@empresaabc.com');
INSERT INTO pessoa (id, nome, endereco, cidade, uf, tipoDocumento, documento) VALUES (nextval('pessoa_seq'), 'Pedro Oliveira', 'Av. das Árvores, 101', 'Porto Alegre', 'RS', 0, '11122233344');
INSERT INTO cliente (id, pessoa_id, email) VALUES (nextval('cliente_seq'), currval('pessoa_seq'), 'pedro@email.com');
INSERT INTO loja (id, nome, endereco, tipoAcesso, urlApi) VALUES (nextval('loja_seq'), 'Food 1', 'Avenida Comercial, 123', 0, 'http://localhost:9091/');
--INSERT INTO loja (id, nome, endereco, tipoAcesso, urlApi) VALUES (nextval('loja_seq'), 'Food 2', 'Rua dos Botecos, 987', 0, 'http://localhost:9092/');
INSERT INTO loja (id, nome, endereco, tipoAcesso, urlApi) VALUES (nextval('loja_seq'), 'Food 2', 'Rua dos Botecos, 987', 1, NULL);
