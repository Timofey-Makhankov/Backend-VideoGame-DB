INSERT INTO role (id, name) VALUES ('a2e40fbe-092b-4ef4-a371-ac3bbf596b70', 'ADMIN') ON CONFLICT DO NOTHING;
INSERT INTO role (id, name) VALUES ('8ba2e7c2-8088-40cd-8201-b800cbc76d45', 'USER') ON CONFLICT DO NOTHING;

INSERT INTO authority (id, name) VALUES ('e57b381b-1764-419e-8fad-46203eeba0fc', 'CREATE') ON CONFLICT DO NOTHING;
INSERT INTO authority (id, name) VALUES ('c9224468-99f5-45fe-9098-80dcbf2ca74b', 'READ') ON CONFLICT DO NOTHING;
INSERT INTO authority (id, name) VALUES ('a5f7dd5b-7ee1-41d5-bb61-a277aafbefba', 'UPDATE') ON CONFLICT DO NOTHING;
INSERT INTO authority (id, name) VALUES ('81253fc3-ccef-4acd-a269-d958a8c5c150', 'DELETE') ON CONFLICT DO NOTHING;

INSERT INTO roles_authorities (role_id, authority_id) VALUES ('a2e40fbe-092b-4ef4-a371-ac3bbf596b70', 'e57b381b-1764-419e-8fad-46203eeba0fc') ON CONFLICT DO NOTHING;
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('a2e40fbe-092b-4ef4-a371-ac3bbf596b70', 'c9224468-99f5-45fe-9098-80dcbf2ca74b') ON CONFLICT DO NOTHING;
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('a2e40fbe-092b-4ef4-a371-ac3bbf596b70', 'a5f7dd5b-7ee1-41d5-bb61-a277aafbefba') ON CONFLICT DO NOTHING;
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('a2e40fbe-092b-4ef4-a371-ac3bbf596b70', '81253fc3-ccef-4acd-a269-d958a8c5c150') ON CONFLICT DO NOTHING;
INSERT INTO roles_authorities (role_id, authority_id) VALUES ('8ba2e7c2-8088-40cd-8201-b800cbc76d45', 'c9224468-99f5-45fe-9098-80dcbf2ca74b') ON CONFLICT DO NOTHING;